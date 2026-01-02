from flask_wtf import FlaskForm
from wtforms import (
    BooleanField,
    FloatField,
    SelectField,
    SelectMultipleField,
    StringField,
    SubmitField,
)
from wtforms.validators import DataRequired, Length, NumberRange, Optional


# 30 most common wood species used in North American woodworking
SPECIES_CHOICES = [
    ("", "Select Species..."),
    # Hardwoods
    ("Red Oak", "Red Oak"),
    ("White Oak", "White Oak"),
    ("Maple", "Hard Maple"),
    ("Walnut", "Walnut"),
    ("Cherry", "Cherry"),
    ("Ash", "Ash"),
    ("Beech", "Beech"),
    ("Birch", "Birch"),
    ("Hickory", "Hickory"),
    ("Elm", "Elm"),
    ("Poplar", "Poplar"),
    ("Alder", "Alder"),
    ("Mahogany", "Mahogany"),
    ("Sapele", "Sapele"),
    ("Sycamore", "Sycamore"),
    # Softwoods
    ("Pine", "Pine"),
    ("Fir", "Fir"),
    ("Cedar", "Cedar"),
]


class LumberForm(FlaskForm):
    """Form for adding or editing lumber entries."""

    species = SelectField(
        "Species",
        choices=SPECIES_CHOICES,
        validators=[DataRequired()],
    )
    length = FloatField(
        "Length (inches)",
        validators=[DataRequired(), NumberRange(min=0.1)],
        render_kw={"placeholder": "Length in inches"},
    )
    width = FloatField(
        "Width (inches)",
        validators=[DataRequired(), NumberRange(min=0.1)],
        render_kw={"placeholder": "Width in inches"},
    )
    thickness = FloatField(
        "Thickness (inches)",
        validators=[DataRequired(), NumberRange(min=0.1)],
        render_kw={"placeholder": "Thickness in inches"},
    )
    planed = BooleanField("Planed", default=False)
    location = SelectField(
        "Location",
        coerce=int,
        validators=[Optional()],
    )
    new_location = StringField(
        "New Location",
        validators=[Optional(), Length(max=100)],
        render_kw={"placeholder": "Or create a new location"},
    )
    tags = SelectMultipleField(
        "Tags",
        coerce=int,
        validators=[Optional()],
    )
    new_tags = StringField(
        "New Tags",
        validators=[Optional(), Length(max=200)],
        render_kw={"placeholder": "Add new tags (comma-separated)"},
    )
    submit = SubmitField("Save")


class SearchForm(FlaskForm):
    """Form for searching and filtering lumber inventory."""

    species = StringField("Species", render_kw={"placeholder": "Filter by species"})
    location = SelectField(
        "Location",
        coerce=str,
        validators=[Optional()],
    )
    planed = SelectField(
        "Surface",
        choices=[("", "All"), ("true", "Planed"), ("false", "Rough")],
        default="",
    )
    tag = SelectField(
        "Tag",
        coerce=str,
        validators=[Optional()],
    )
    min_length = FloatField("Min Length", validators=[NumberRange(min=0)], default=None)
    max_length = FloatField("Max Length", validators=[NumberRange(min=0)], default=None)
    submit = SubmitField("Search")
