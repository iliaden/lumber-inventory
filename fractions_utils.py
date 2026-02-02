"""Utility functions for parsing and formatting fractions in lumber dimensions."""

import re
from fractions import Fraction


def parse_fraction_string(value):
    """
    Parse a string that may contain fractions and convert to float.

    Supports formats like:
    - "1.75" -> 1.75
    - "1 3/4" -> 1.75
    - "3/4" -> 0.75
    - "1-3/4" -> 1.75
    - "2" -> 2.0

    Args:
        value: String or numeric value to parse

    Returns:
        Float value

    Raises:
        ValueError: If the value cannot be parsed
    """
    if value is None:
        raise ValueError("Value cannot be None")

    if isinstance(value, (int, float)):
        return float(value)

    value = str(value).strip()

    if not value:
        raise ValueError("Value cannot be empty")

    try:
        return float(value)
    except ValueError:
        pass

    pattern = r'^(\d+)[\s\-]+(\d+)/(\d+)$'
    match = re.match(pattern, value)
    if match:
        whole = int(match.group(1))
        numerator = int(match.group(2))
        denominator = int(match.group(3))
        if denominator == 0:
            raise ValueError("Denominator cannot be zero")
        return float(whole) + float(numerator) / float(denominator)

    pattern = r'^(\d+)/(\d+)$'
    match = re.match(pattern, value)
    if match:
        numerator = int(match.group(1))
        denominator = int(match.group(2))
        if denominator == 0:
            raise ValueError("Denominator cannot be zero")
        return float(numerator) / float(denominator)

    raise ValueError(f"Cannot parse '{value}' as a number or fraction")


def float_to_fraction_string(value, max_denominator=64):
    """
    Convert a float to a human-readable fraction string.

    Uses common woodworking fractions (halves, quarters, eighths, sixteenths, etc.)

    Args:
        value: Float value to convert
        max_denominator: Maximum denominator to use (default 64 for 1/64")

    Returns:
        String representation (e.g., "1 3/4" or "3/4" or "2")
    """
    if value is None:
        return ""

    value = float(value)

    if value < 0:
        return f"-{float_to_fraction_string(-value, max_denominator)}"

    whole_part = int(value)
    decimal_part = value - whole_part

    if decimal_part < 0.001:
        return str(whole_part) if whole_part > 0 else "0"

    frac = Fraction(decimal_part).limit_denominator(max_denominator)

    if frac.numerator == 0:
        return str(whole_part) if whole_part > 0 else "0"

    if whole_part > 0:
        return f"{whole_part} {frac.numerator}/{frac.denominator}"
    else:
        return f"{frac.numerator}/{frac.denominator}"


COMMON_FRACTIONS = {
    # 32nds
    0.03125: "1/32",
    0.09375: "3/32",
    0.15625: "5/32",
    0.21875: "7/32",
    0.28125: "9/32",
    0.34375: "11/32",
    0.40625: "13/32",
    0.46875: "15/32",
    0.53125: "17/32",
    0.59375: "19/32",
    0.65625: "21/32",
    0.71875: "23/32",
    0.78125: "25/32",
    0.84375: "27/32",
    0.90625: "29/32",
    0.96875: "31/32",
    # 16ths (also includes 8ths, 4ths, halves)
    0.0625: "1/16",
    0.125: "1/8",
    0.1875: "3/16",
    0.25: "1/4",
    0.3125: "5/16",
    0.375: "3/8",
    0.4375: "7/16",
    0.5: "1/2",
    0.5625: "9/16",
    0.625: "5/8",
    0.6875: "11/16",
    0.75: "3/4",
    0.8125: "13/16",
    0.875: "7/8",
    0.9375: "15/16",
}


def float_to_fraction_display(value, tolerance=0.01):
    """
    Convert a float to a fraction string, preferring common woodworking fractions.

    Args:
        value: Float value to convert
        tolerance: How close the decimal must be to match a common fraction

    Returns:
        String representation using common fractions when possible
    """
    if value is None:
        return ""

    value = float(value)

    if value < 0:
        return f"-{float_to_fraction_display(-value, tolerance)}"

    whole_part = int(value)
    decimal_part = round(value - whole_part, 6)

    if decimal_part < 0.001:
        return str(whole_part) if whole_part > 0 else "0"

    for frac_decimal, frac_str in COMMON_FRACTIONS.items():
        if abs(decimal_part - frac_decimal) < tolerance:
            if whole_part > 0:
                return f"{whole_part} {frac_str}"
            else:
                return frac_str

    return float_to_fraction_string(value)
