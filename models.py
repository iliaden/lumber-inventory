from datetime import datetime

from flask_sqlalchemy import SQLAlchemy

db = SQLAlchemy()

# Association table for many-to-many relationship between Lumber and Tag
lumber_tags = db.Table(
    "lumber_tags",
    db.Column("lumber_id", db.Integer, db.ForeignKey("lumber.id"), primary_key=True),
    db.Column("tag_id", db.Integer, db.ForeignKey("tag.id"), primary_key=True),
)


class Tag(db.Model):
    """Model representing a tag that can be applied to lumber."""

    __tablename__ = "tag"

    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(50), unique=True, nullable=False)

    def __repr__(self):
        return f"<Tag {self.id}: {self.name}>"

    def to_dict(self):
        """Convert tag instance to dictionary."""
        return {
            "id": self.id,
            "name": self.name,
        }


class Location(db.Model):
    """Model representing a storage location for lumber."""

    __tablename__ = "location"

    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(100), unique=True, nullable=False)

    # Relationship to lumber items at this location
    lumber_items = db.relationship("Lumber", backref="location_ref", lazy=True)

    def __repr__(self):
        return f"<Location {self.id}: {self.name}>"

    def to_dict(self):
        """Convert location instance to dictionary."""
        return {
            "id": self.id,
            "name": self.name,
        }


class Lumber(db.Model):
    """Model representing a piece of lumber in inventory."""

    __tablename__ = "lumber"

    id = db.Column(db.Integer, primary_key=True)
    species = db.Column(db.String(100), nullable=False)
    length = db.Column(db.Float, nullable=False)
    width = db.Column(db.Float, nullable=False)
    thickness = db.Column(db.Float, nullable=False)
    planed = db.Column(db.Boolean, default=False, nullable=False)
    location_id = db.Column(db.Integer, db.ForeignKey("location.id"), nullable=True)
    date_added = db.Column(db.DateTime, default=datetime.utcnow, nullable=False)

    # Many-to-many relationship with tags
    tags = db.relationship(
        "Tag",
        secondary=lumber_tags,
        lazy="subquery",
        backref=db.backref("lumber_items", lazy=True),
    )

    @property
    def location(self):
        """Get location name for backward compatibility."""
        return self.location_ref.name if self.location_ref else None

    def __repr__(self):
        return f"<Lumber {self.id}: {self.species} {self.length}x{self.width}x{self.thickness}>"

    def to_dict(self):
        """Convert lumber instance to dictionary."""
        return {
            "id": self.id,
            "species": self.species,
            "length": self.length,
            "width": self.width,
            "thickness": self.thickness,
            "planed": self.planed,
            "location": self.location,
            "location_id": self.location_id,
            "date_added": self.date_added.isoformat() if self.date_added else None,
            "tags": [tag.name for tag in self.tags],
        }
