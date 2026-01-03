import os

from flask import flash, Flask, jsonify, redirect, render_template, request, url_for
from forms import LumberForm, SearchForm
from fractions_utils import float_to_fraction_display, parse_fraction_string
from models import db, Location, Lumber, Tag

app = Flask(__name__)
app.config["SECRET_KEY"] = os.environ.get(
    "SECRET_KEY", "dev-secret-key-change-in-production"
)
app.config["SQLALCHEMY_DATABASE_URI"] = "sqlite:///inventory.db"
app.config["SQLALCHEMY_TRACK_MODIFICATIONS"] = False

db.init_app(app)


@app.template_filter("fraction")
def fraction_filter(value):
    """Jinja2 filter to convert float to fraction display."""
    return float_to_fraction_display(value)


with app.app_context():
    db.create_all()


@app.route("/")
def index():
    """Display inventory with optional search/filter."""
    form = SearchForm(request.args, meta={"csrf": False})

    # Populate tag choices for the search form
    all_tags = Tag.query.order_by(Tag.name).all()
    form.tag.choices = [("", "All Tags")] + [(str(t.id), t.name) for t in all_tags]

    # Populate location choices for the search form
    all_locations = Location.query.order_by(Location.name).all()
    form.location.choices = [("", "All Locations")] + [
        (str(loc.id), loc.name) for loc in all_locations
    ]

    query = Lumber.query

    if request.args.get("species"):
        query = query.filter(Lumber.species.ilike(f"%{request.args.get('species')}%"))

    if request.args.get("location"):
        try:
            location_id = int(request.args.get("location"))
            query = query.filter(Lumber.location_id == location_id)
        except ValueError:
            pass

    if request.args.get("planed"):
        planed_value = request.args.get("planed") == "true"
        query = query.filter(Lumber.planed == planed_value)

    if request.args.get("tag"):
        try:
            tag_id = int(request.args.get("tag"))
            query = query.filter(Lumber.tags.any(Tag.id == tag_id))
        except ValueError:
            pass

    if request.args.get("min_length"):
        try:
            min_len = float(request.args.get("min_length"))
            query = query.filter(Lumber.length >= min_len)
        except ValueError:
            pass

    if request.args.get("max_length"):
        try:
            max_len = float(request.args.get("max_length"))
            query = query.filter(Lumber.length <= max_len)
        except ValueError:
            pass

    lumber_items = query.order_by(Lumber.date_added.desc()).all()
    return render_template("index.html", lumber_items=lumber_items, form=form)


def get_or_create_tags(tag_names):
    """Get existing tags or create new ones from a list of tag names."""
    tags = []
    for name in tag_names:
        name = name.strip()
        if not name:
            continue
        tag = Tag.query.filter(Tag.name.ilike(name)).first()
        if not tag:
            tag = Tag(name=name)
            db.session.add(tag)
        tags.append(tag)
    return tags


def get_or_create_location(location_name):
    """Get existing location or create a new one from a location name."""
    location_name = location_name.strip()
    if not location_name:
        return None
    location = Location.query.filter(Location.name.ilike(location_name)).first()
    if not location:
        location = Location(name=location_name)
        db.session.add(location)
    return location


@app.route("/add", methods=["GET", "POST"])
def add_lumber():
    """Add a new lumber entry."""
    form = LumberForm()

    # Populate tag choices
    all_tags = Tag.query.order_by(Tag.name).all()
    form.tags.choices = [(t.id, t.name) for t in all_tags]

    # Populate location choices
    all_locations = Location.query.order_by(Location.name).all()
    form.location.choices = [(0, "Select Location...")] + [
        (loc.id, loc.name) for loc in all_locations
    ]

    if form.validate_on_submit():
        lumber = Lumber(
            species=form.species.data,
            length=parse_fraction_string(form.length.data),
            width=parse_fraction_string(form.width.data),
            thickness=parse_fraction_string(form.thickness.data),
            planed=form.planed.data,
        )

        # Handle location - prefer new location if provided, otherwise use selected
        if form.new_location.data and form.new_location.data.strip():
            location = get_or_create_location(form.new_location.data)
            if location:
                lumber.location_id = location.id
        elif form.location.data and form.location.data != 0:
            lumber.location_id = form.location.data

        # Handle existing tags
        if form.tags.data:
            existing_tags = Tag.query.filter(Tag.id.in_(form.tags.data)).all()
            lumber.tags.extend(existing_tags)

        # Handle new tags
        if form.new_tags.data:
            new_tag_names = form.new_tags.data.split(",")
            new_tags = get_or_create_tags(new_tag_names)
            for tag in new_tags:
                if tag not in lumber.tags:
                    lumber.tags.append(tag)

        db.session.add(lumber)
        db.session.commit()
        flash("Lumber added successfully!", "success")
        return redirect(url_for("index"))
    return render_template("add_lumber.html", form=form)


@app.route("/edit/<int:id>", methods=["GET", "POST"])
def edit_lumber(id):
    """Edit an existing lumber entry."""
    lumber = Lumber.query.get_or_404(id)
    form = LumberForm(obj=lumber)

    # Populate tag choices
    all_tags = Tag.query.order_by(Tag.name).all()
    form.tags.choices = [(t.id, t.name) for t in all_tags]

    # Populate location choices
    all_locations = Location.query.order_by(Location.name).all()
    form.location.choices = [(0, "Select Location...")] + [
        (loc.id, loc.name) for loc in all_locations
    ]

    if request.method == "GET":
        # Pre-select current tags
        form.tags.data = [t.id for t in lumber.tags]
        # Pre-select current location
        form.location.data = lumber.location_id if lumber.location_id else 0
        # Convert dimensions to fraction strings for display
        form.length.data = float_to_fraction_display(lumber.length)
        form.width.data = float_to_fraction_display(lumber.width)
        form.thickness.data = float_to_fraction_display(lumber.thickness)

    if form.validate_on_submit():
        lumber.species = form.species.data
        lumber.length = parse_fraction_string(form.length.data)
        lumber.width = parse_fraction_string(form.width.data)
        lumber.thickness = parse_fraction_string(form.thickness.data)
        lumber.planed = form.planed.data

        # Handle location - prefer new location if provided, otherwise use selected
        if form.new_location.data and form.new_location.data.strip():
            location = get_or_create_location(form.new_location.data)
            if location:
                lumber.location_id = location.id
        elif form.location.data and form.location.data != 0:
            lumber.location_id = form.location.data
        else:
            lumber.location_id = None

        # Clear existing tags and set new ones
        lumber.tags.clear()

        # Handle existing tags
        if form.tags.data:
            existing_tags = Tag.query.filter(Tag.id.in_(form.tags.data)).all()
            lumber.tags.extend(existing_tags)

        # Handle new tags
        if form.new_tags.data:
            new_tag_names = form.new_tags.data.split(",")
            new_tags = get_or_create_tags(new_tag_names)
            for tag in new_tags:
                if tag not in lumber.tags:
                    lumber.tags.append(tag)

        db.session.commit()
        flash("Lumber updated successfully!", "success")
        return redirect(url_for("index"))
    return render_template("edit_lumber.html", form=form, lumber=lumber)


@app.route("/delete/<int:id>", methods=["POST"])
def delete_lumber(id):
    """Delete a lumber entry."""
    lumber = Lumber.query.get_or_404(id)
    db.session.delete(lumber)
    db.session.commit()
    flash("Lumber deleted successfully!", "success")
    return redirect(url_for("index"))


if __name__ == "__main__":
    app.run(host="0.0.0.0", port=80, debug=True)
