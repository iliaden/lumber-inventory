from flask_wtf import FlaskForm
from wtforms import (
    BooleanField,
    FloatField,
    SelectField,
    SelectMultipleField,
    StringField,
    SubmitField,
)
from wtforms.validators import DataRequired, Length, NumberRange, Optional, ValidationError

from fractions_utils import parse_fraction_string, float_to_fraction_display


class FractionValidator:
    """Validator that accepts fractions like '1 3/4' or '3/4' as well as decimals."""

    def __init__(self, min_value=None, message=None):
        self.min_value = min_value
        self.message = message

    def __call__(self, form, field):
        if not field.data or field.data.strip() == "":
            raise ValidationError("This field is required.")

        try:
            value = parse_fraction_string(field.data)
            if self.min_value is not None and value < self.min_value:
                raise ValidationError(
                    self.message or f"Value must be at least {self.min_value}."
                )
        except ValueError as e:
            raise ValidationError(
                f"Invalid format. Enter a number (e.g., 1.5) or fraction (e.g., 1 1/2)."
            )


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
    length = StringField(
        "Length (inches)",
        validators=[FractionValidator(min_value=0.1)],
        render_kw={"placeholder": "e.g., 48 or 48 1/2"},
    )
    width = StringField(
        "Width (inches)",
        validators=[FractionValidator(min_value=0.1)],
        render_kw={"placeholder": "e.g., 6 or 5 3/4"},
    )
    thickness = StringField(
        "Thickness (inches)",
        validators=[FractionValidator(min_value=0.1)],
        render_kw={"placeholder": "e.g., 1 or 3/4"},
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
    min_length = StringField("Min Length", validators=[Optional()])
    max_length = StringField("Max Length", validators=[Optional()])
    min_thickness = StringField("Min Thickness", validators=[Optional()])
    max_thickness = StringField("Max Thickness", validators=[Optional()])
    submit = SubmitField("Search")
