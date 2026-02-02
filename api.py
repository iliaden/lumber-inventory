"""
REST API for Lumber Inventory System.

This module provides JSON API endpoints for the lumber inventory system,
designed to be consumed by mobile applications (e.g., Android app).

All endpoints are prefixed with /api/v1/
"""

from flask import Blueprint, jsonify, request
from fractions_utils import float_to_fraction_display, parse_fraction_string
from models import db, Location, Lumber, Tag

api = Blueprint("api", __name__, url_prefix="/api/v1")


def error_response(message, status_code=400):
    """Create a standardized error response."""
    return jsonify({"error": message}), status_code


def success_response(data=None, message=None, status_code=200):
    """Create a standardized success response."""
    response = {"success": True}
    if message:
        response["message"] = message
    if data is not None:
        response["data"] = data
    return jsonify(response), status_code


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


def lumber_to_dict_with_fractions(lumber):
    """Convert lumber to dict with fraction display values included."""
    data = lumber.to_dict()
    data["length_display"] = float_to_fraction_display(lumber.length)
    data["width_display"] = float_to_fraction_display(lumber.width)
    data["thickness_display"] = float_to_fraction_display(lumber.thickness)
    return data


# ============================================================================
# Lumber Endpoints
# ============================================================================


@api.route("/lumber", methods=["GET"])
def get_lumber_list():
    """
    Get list of all lumber items with optional filtering.

    Query Parameters:
        species (str): Filter by species (partial match)
        location_id (int): Filter by location ID
        planed (bool): Filter by planed status ("true" or "false")
        tag_id (int): Filter by tag ID
        min_length (str): Minimum length (supports fractions like "1 3/4")
        max_length (str): Maximum length (supports fractions)
        min_thickness (str): Minimum thickness (supports fractions)
        max_thickness (str): Maximum thickness (supports fractions)

    Returns:
        JSON array of lumber items
    """
    query = Lumber.query

    if request.args.get("species"):
        query = query.filter(Lumber.species.ilike(f"%{request.args.get('species')}%"))

    if request.args.get("location_id"):
        try:
            location_id = int(request.args.get("location_id"))
            query = query.filter(Lumber.location_id == location_id)
        except ValueError:
            return error_response("Invalid location_id parameter")

    if request.args.get("planed"):
        planed_value = request.args.get("planed").lower() == "true"
        query = query.filter(Lumber.planed == planed_value)

    if request.args.get("tag_id"):
        try:
            tag_id = int(request.args.get("tag_id"))
            query = query.filter(Lumber.tags.any(Tag.id == tag_id))
        except ValueError:
            return error_response("Invalid tag_id parameter")

    if request.args.get("min_length"):
        try:
            min_len = parse_fraction_string(request.args.get("min_length"))
            query = query.filter(Lumber.length >= min_len)
        except ValueError:
            return error_response("Invalid min_length parameter")

    if request.args.get("max_length"):
        try:
            max_len = parse_fraction_string(request.args.get("max_length"))
            query = query.filter(Lumber.length <= max_len)
        except ValueError:
            return error_response("Invalid max_length parameter")

    if request.args.get("min_thickness"):
        try:
            min_thick = parse_fraction_string(request.args.get("min_thickness"))
            query = query.filter(Lumber.thickness >= min_thick)
        except ValueError:
            return error_response("Invalid min_thickness parameter")

    if request.args.get("max_thickness"):
        try:
            max_thick = parse_fraction_string(request.args.get("max_thickness"))
            query = query.filter(Lumber.thickness <= max_thick)
        except ValueError:
            return error_response("Invalid max_thickness parameter")

    lumber_items = query.order_by(Lumber.date_added.desc()).all()
    return success_response(
        data=[lumber_to_dict_with_fractions(item) for item in lumber_items]
    )


@api.route("/lumber/<int:id>", methods=["GET"])
def get_lumber(id):
    """
    Get a single lumber item by ID.

    Args:
        id: Lumber item ID

    Returns:
        JSON object with lumber details
    """
    lumber = Lumber.query.get(id)
    if not lumber:
        return error_response("Lumber not found", 404)
    return success_response(data=lumber_to_dict_with_fractions(lumber))


@api.route("/lumber", methods=["POST"])
def create_lumber():
    """
    Create a new lumber item.

    Request Body (JSON):
        species (str, required): Wood species
        length (str/float, required): Length in inches (supports fractions)
        width (str/float, required): Width in inches (supports fractions)
        thickness (str/float, required): Thickness in inches (supports fractions)
        planed (bool, optional): Whether wood is planed (default: false)
        location_id (int, optional): Existing location ID
        location_name (str, optional): New location name (creates if not exists)
        tags (list[str], optional): List of tag names (creates if not exist)
        tag_ids (list[int], optional): List of existing tag IDs

    Returns:
        JSON object with created lumber details
    """
    data = request.get_json()
    if not data:
        return error_response("Request body must be JSON")

    required_fields = ["species", "length", "width", "thickness"]
    for field in required_fields:
        if field not in data:
            return error_response(f"Missing required field: {field}")

    try:
        length = parse_fraction_string(data["length"])
        width = parse_fraction_string(data["width"])
        thickness = parse_fraction_string(data["thickness"])
    except ValueError as e:
        return error_response(f"Invalid dimension value: {str(e)}")

    lumber = Lumber(
        species=data["species"],
        length=length,
        width=width,
        thickness=thickness,
        planed=data.get("planed", False),
    )

    if data.get("location_name"):
        location = get_or_create_location(data["location_name"])
        if location:
            db.session.flush()
            lumber.location_id = location.id
    elif data.get("location_id"):
        location = Location.query.get(data["location_id"])
        if location:
            lumber.location_id = location.id
        else:
            return error_response("Location not found", 404)

    if data.get("tag_ids"):
        existing_tags = Tag.query.filter(Tag.id.in_(data["tag_ids"])).all()
        lumber.tags.extend(existing_tags)

    if data.get("tags"):
        new_tags = get_or_create_tags(data["tags"])
        for tag in new_tags:
            if tag not in lumber.tags:
                lumber.tags.append(tag)

    db.session.add(lumber)
    db.session.commit()

    return success_response(
        data=lumber_to_dict_with_fractions(lumber),
        message="Lumber created successfully",
        status_code=201,
    )


@api.route("/lumber/<int:id>", methods=["PUT"])
def update_lumber(id):
    """
    Update an existing lumber item.

    Args:
        id: Lumber item ID

    Request Body (JSON):
        species (str, optional): Wood species
        length (str/float, optional): Length in inches (supports fractions)
        width (str/float, optional): Width in inches (supports fractions)
        thickness (str/float, optional): Thickness in inches (supports fractions)
        planed (bool, optional): Whether wood is planed
        location_id (int, optional): Existing location ID (null to clear)
        location_name (str, optional): New location name (creates if not exists)
        tags (list[str], optional): List of tag names (replaces existing tags)
        tag_ids (list[int], optional): List of existing tag IDs (replaces existing tags)

    Returns:
        JSON object with updated lumber details
    """
    lumber = Lumber.query.get(id)
    if not lumber:
        return error_response("Lumber not found", 404)

    data = request.get_json()
    if not data:
        return error_response("Request body must be JSON")

    if "species" in data:
        lumber.species = data["species"]

    try:
        if "length" in data:
            lumber.length = parse_fraction_string(data["length"])
        if "width" in data:
            lumber.width = parse_fraction_string(data["width"])
        if "thickness" in data:
            lumber.thickness = parse_fraction_string(data["thickness"])
    except ValueError as e:
        return error_response(f"Invalid dimension value: {str(e)}")

    if "planed" in data:
        lumber.planed = data["planed"]

    if "location_name" in data and data["location_name"]:
        location = get_or_create_location(data["location_name"])
        if location:
            db.session.flush()
            lumber.location_id = location.id
    elif "location_id" in data:
        if data["location_id"] is None:
            lumber.location_id = None
        else:
            location = Location.query.get(data["location_id"])
            if location:
                lumber.location_id = location.id
            else:
                return error_response("Location not found", 404)

    if "tags" in data or "tag_ids" in data:
        lumber.tags.clear()

        if data.get("tag_ids"):
            existing_tags = Tag.query.filter(Tag.id.in_(data["tag_ids"])).all()
            lumber.tags.extend(existing_tags)

        if data.get("tags"):
            new_tags = get_or_create_tags(data["tags"])
            for tag in new_tags:
                if tag not in lumber.tags:
                    lumber.tags.append(tag)

    db.session.commit()

    return success_response(
        data=lumber_to_dict_with_fractions(lumber),
        message="Lumber updated successfully",
    )


@api.route("/lumber/<int:id>", methods=["DELETE"])
def delete_lumber(id):
    """
    Delete a lumber item.

    Args:
        id: Lumber item ID

    Returns:
        JSON success message
    """
    lumber = Lumber.query.get(id)
    if not lumber:
        return error_response("Lumber not found", 404)

    db.session.delete(lumber)
    db.session.commit()

    return success_response(message="Lumber deleted successfully")


# ============================================================================
# Location Endpoints
# ============================================================================


@api.route("/locations", methods=["GET"])
def get_locations():
    """
    Get list of all storage locations.

    Returns:
        JSON array of locations
    """
    locations = Location.query.order_by(Location.name).all()
    return success_response(data=[loc.to_dict() for loc in locations])


@api.route("/locations/<int:id>", methods=["GET"])
def get_location(id):
    """
    Get a single location by ID.

    Args:
        id: Location ID

    Returns:
        JSON object with location details
    """
    location = Location.query.get(id)
    if not location:
        return error_response("Location not found", 404)
    return success_response(data=location.to_dict())


@api.route("/locations", methods=["POST"])
def create_location():
    """
    Create a new storage location.

    Request Body (JSON):
        name (str, required): Location name

    Returns:
        JSON object with created location details
    """
    data = request.get_json()
    if not data:
        return error_response("Request body must be JSON")

    if "name" not in data or not data["name"].strip():
        return error_response("Missing required field: name")

    existing = Location.query.filter(Location.name.ilike(data["name"].strip())).first()
    if existing:
        return error_response("Location with this name already exists", 409)

    location = Location(name=data["name"].strip())
    db.session.add(location)
    db.session.commit()

    return success_response(
        data=location.to_dict(),
        message="Location created successfully",
        status_code=201,
    )


@api.route("/locations/<int:id>", methods=["PUT"])
def update_location(id):
    """
    Update an existing location.

    Args:
        id: Location ID

    Request Body (JSON):
        name (str, required): New location name

    Returns:
        JSON object with updated location details
    """
    location = Location.query.get(id)
    if not location:
        return error_response("Location not found", 404)

    data = request.get_json()
    if not data:
        return error_response("Request body must be JSON")

    if "name" not in data or not data["name"].strip():
        return error_response("Missing required field: name")

    existing = Location.query.filter(
        Location.name.ilike(data["name"].strip()), Location.id != id
    ).first()
    if existing:
        return error_response("Location with this name already exists", 409)

    location.name = data["name"].strip()
    db.session.commit()

    return success_response(
        data=location.to_dict(), message="Location updated successfully"
    )


@api.route("/locations/<int:id>", methods=["DELETE"])
def delete_location(id):
    """
    Delete a storage location.

    Note: This will set location_id to NULL for any lumber at this location.

    Args:
        id: Location ID

    Returns:
        JSON success message
    """
    location = Location.query.get(id)
    if not location:
        return error_response("Location not found", 404)

    Lumber.query.filter(Lumber.location_id == id).update({"location_id": None})

    db.session.delete(location)
    db.session.commit()

    return success_response(message="Location deleted successfully")


# ============================================================================
# Tag Endpoints
# ============================================================================


@api.route("/tags", methods=["GET"])
def get_tags():
    """
    Get list of all tags.

    Returns:
        JSON array of tags
    """
    tags = Tag.query.order_by(Tag.name).all()
    return success_response(data=[tag.to_dict() for tag in tags])


@api.route("/tags/<int:id>", methods=["GET"])
def get_tag(id):
    """
    Get a single tag by ID.

    Args:
        id: Tag ID

    Returns:
        JSON object with tag details
    """
    tag = Tag.query.get(id)
    if not tag:
        return error_response("Tag not found", 404)
    return success_response(data=tag.to_dict())


@api.route("/tags", methods=["POST"])
def create_tag():
    """
    Create a new tag.

    Request Body (JSON):
        name (str, required): Tag name

    Returns:
        JSON object with created tag details
    """
    data = request.get_json()
    if not data:
        return error_response("Request body must be JSON")

    if "name" not in data or not data["name"].strip():
        return error_response("Missing required field: name")

    existing = Tag.query.filter(Tag.name.ilike(data["name"].strip())).first()
    if existing:
        return error_response("Tag with this name already exists", 409)

    tag = Tag(name=data["name"].strip())
    db.session.add(tag)
    db.session.commit()

    return success_response(
        data=tag.to_dict(), message="Tag created successfully", status_code=201
    )


@api.route("/tags/<int:id>", methods=["PUT"])
def update_tag(id):
    """
    Update an existing tag.

    Args:
        id: Tag ID

    Request Body (JSON):
        name (str, required): New tag name

    Returns:
        JSON object with updated tag details
    """
    tag = Tag.query.get(id)
    if not tag:
        return error_response("Tag not found", 404)

    data = request.get_json()
    if not data:
        return error_response("Request body must be JSON")

    if "name" not in data or not data["name"].strip():
        return error_response("Missing required field: name")

    existing = Tag.query.filter(
        Tag.name.ilike(data["name"].strip()), Tag.id != id
    ).first()
    if existing:
        return error_response("Tag with this name already exists", 409)

    tag.name = data["name"].strip()
    db.session.commit()

    return success_response(data=tag.to_dict(), message="Tag updated successfully")


@api.route("/tags/<int:id>", methods=["DELETE"])
def delete_tag(id):
    """
    Delete a tag.

    Note: This will remove the tag from any lumber items that have it.

    Args:
        id: Tag ID

    Returns:
        JSON success message
    """
    tag = Tag.query.get(id)
    if not tag:
        return error_response("Tag not found", 404)

    db.session.delete(tag)
    db.session.commit()

    return success_response(message="Tag deleted successfully")


# ============================================================================
# Utility Endpoints
# ============================================================================


@api.route("/health", methods=["GET"])
def health_check():
    """
    Health check endpoint.

    Returns:
        JSON object with status information
    """
    return success_response(data={"status": "healthy", "version": "1.0.0"})


@api.route("/stats", methods=["GET"])
def get_stats():
    """
    Get inventory statistics.

    Returns:
        JSON object with statistics
    """
    total_lumber = Lumber.query.count()
    total_locations = Location.query.count()
    total_tags = Tag.query.count()

    planed_count = Lumber.query.filter(Lumber.planed == True).count()
    rough_count = Lumber.query.filter(Lumber.planed == False).count()

    species_counts = (
        db.session.query(Lumber.species, db.func.count(Lumber.id))
        .group_by(Lumber.species)
        .all()
    )

    return success_response(
        data={
            "total_lumber": total_lumber,
            "total_locations": total_locations,
            "total_tags": total_tags,
            "planed_count": planed_count,
            "rough_count": rough_count,
            "species_counts": {species: count for species, count in species_counts},
        }
    )


@api.route("/species", methods=["GET"])
def get_species():
    """
    Get list of all unique wood species currently in the inventory.

    Returns:
        JSON array of species names (strings)
    """
    species_list = (
        db.session.query(Lumber.species)
        .distinct()
        .order_by(Lumber.species)
        .all()
    )
    return success_response(data=[species[0] for species in species_list])
