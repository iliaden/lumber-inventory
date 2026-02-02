package com.lumber.inventory.util

/**
 * Utility class for parsing and displaying fractional dimensions.
 * Ported from Python fractions_utils.py to match web app behavior.
 */
object FractionUtils {
    
    /**
     * Common woodworking fractions mapped to their decimal equivalents.
     * Used for displaying dimensions in familiar fractional format.
     */
    private val COMMON_FRACTIONS = mapOf(
        // 32nds
        0.03125 to "1/32",
        0.09375 to "3/32",
        0.15625 to "5/32",
        0.21875 to "7/32",
        0.28125 to "9/32",
        0.34375 to "11/32",
        0.40625 to "13/32",
        0.46875 to "15/32",
        0.53125 to "17/32",
        0.59375 to "19/32",
        0.65625 to "21/32",
        0.71875 to "23/32",
        0.78125 to "25/32",
        0.84375 to "27/32",
        0.90625 to "29/32",
        0.96875 to "31/32",
        // 16ths (also includes 8ths, 4ths, halves)
        0.0625 to "1/16",
        0.125 to "1/8",
        0.1875 to "3/16",
        0.25 to "1/4",
        0.3125 to "5/16",
        0.375 to "3/8",
        0.4375 to "7/16",
        0.5 to "1/2",
        0.5625 to "9/16",
        0.625 to "5/8",
        0.6875 to "11/16",
        0.75 to "3/4",
        0.8125 to "13/16",
        0.875 to "7/8",
        0.9375 to "15/16"
    )
    
    /**
     * Parse a string that may contain fractions and convert to Double.
     * 
     * Supports formats:
     * - "1.75" -> 1.75
     * - "1 3/4" -> 1.75
     * - "1-3/4" -> 1.75
     * - "3/4" -> 0.75
     * - "2" -> 2.0
     * 
     * @param value String value to parse
     * @return Double value, or null if parsing fails
     */
    fun parseFraction(value: String?): Double? {
        if (value.isNullOrBlank()) return null
        
        val trimmed = value.trim()
        
        // Try parsing as a plain number first
        trimmed.toDoubleOrNull()?.let { return it }
        
        // Try parsing as mixed number with space: "1 3/4"
        val mixedSpacePattern = Regex("""^(\d+)\s+(\d+)/(\d+)$""")
        mixedSpacePattern.matchEntire(trimmed)?.let { match ->
            val whole = match.groupValues[1].toIntOrNull() ?: return null
            val numerator = match.groupValues[2].toIntOrNull() ?: return null
            val denominator = match.groupValues[3].toIntOrNull() ?: return null
            if (denominator == 0) return null
            return whole.toDouble() + numerator.toDouble() / denominator.toDouble()
        }
        
        // Try parsing as mixed number with dash: "1-3/4"
        val mixedDashPattern = Regex("""^(\d+)-(\d+)/(\d+)$""")
        mixedDashPattern.matchEntire(trimmed)?.let { match ->
            val whole = match.groupValues[1].toIntOrNull() ?: return null
            val numerator = match.groupValues[2].toIntOrNull() ?: return null
            val denominator = match.groupValues[3].toIntOrNull() ?: return null
            if (denominator == 0) return null
            return whole.toDouble() + numerator.toDouble() / denominator.toDouble()
        }
        
        // Try parsing as simple fraction: "3/4"
        val fractionPattern = Regex("""^(\d+)/(\d+)$""")
        fractionPattern.matchEntire(trimmed)?.let { match ->
            val numerator = match.groupValues[1].toIntOrNull() ?: return null
            val denominator = match.groupValues[2].toIntOrNull() ?: return null
            if (denominator == 0) return null
            return numerator.toDouble() / denominator.toDouble()
        }
        
        return null
    }
    
    /**
     * Check if a string is a valid fraction/dimension format.
     * 
     * @param value String to validate
     * @return true if the string can be parsed as a dimension
     */
    fun isValidFraction(value: String?): Boolean {
        return parseFraction(value) != null
    }
    
    /**
     * Convert a Double to a fraction display string, preferring common fractions.
     * 
     * Examples:
     * - 1.75 -> "1 3/4"
     * - 0.75 -> "3/4"
     * - 2.0 -> "2"
     * - 48.0 -> "48"
     * 
     * @param value Double value to convert
     * @param tolerance How close the decimal must be to match a common fraction
     * @return String representation using fractions when possible
     */
    fun toFractionDisplay(value: Double?, tolerance: Double = 0.01): String {
        if (value == null) return ""
        
        if (value < 0) {
            return "-${toFractionDisplay(-value, tolerance)}"
        }
        
        val wholePart = value.toInt()
        val decimalPart = value - wholePart
        
        // If decimal part is negligible, just return whole number
        if (decimalPart < 0.001) {
            return if (wholePart > 0) wholePart.toString() else "0"
        }
        
        // Try to match with common fractions
        for ((fracDecimal, fracStr) in COMMON_FRACTIONS) {
            if (kotlin.math.abs(decimalPart - fracDecimal) < tolerance) {
                return if (wholePart > 0) {
                    "$wholePart $fracStr"
                } else {
                    fracStr
                }
            }
        }
        
        // Fall back to generic fraction conversion
        return toFractionString(value)
    }
    
    /**
     * Convert a Double to a fraction string using continued fraction algorithm.
     * 
     * @param value Double value to convert
     * @param maxDenominator Maximum denominator to use
     * @return String representation as a fraction
     */
    private fun toFractionString(value: Double, maxDenominator: Int = 64): String {
        val wholePart = value.toInt()
        val decimalPart = value - wholePart
        
        if (decimalPart < 0.001) {
            return if (wholePart > 0) wholePart.toString() else "0"
        }
        
        // Use continued fraction algorithm to find best rational approximation
        val (numerator, denominator) = toRationalApproximation(decimalPart, maxDenominator)
        
        if (numerator == 0) {
            return if (wholePart > 0) wholePart.toString() else "0"
        }
        
        return if (wholePart > 0) {
            "$wholePart $numerator/$denominator"
        } else {
            "$numerator/$denominator"
        }
    }
    
    /**
     * Find the best rational approximation for a decimal value.
     * Uses the Stern-Brocot tree / mediant approach.
     * 
     * @param decimal Decimal value between 0 and 1
     * @param maxDenominator Maximum denominator allowed
     * @return Pair of (numerator, denominator)
     */
    private fun toRationalApproximation(decimal: Double, maxDenominator: Int): Pair<Int, Int> {
        if (decimal <= 0) return Pair(0, 1)
        if (decimal >= 1) return Pair(1, 1)
        
        var lowerNum = 0
        var lowerDen = 1
        var upperNum = 1
        var upperDen = 1
        
        while (true) {
            val midNum = lowerNum + upperNum
            val midDen = lowerDen + upperDen
            
            if (midDen > maxDenominator) {
                // Return the closer of lower or upper
                val lowerDiff = kotlin.math.abs(decimal - lowerNum.toDouble() / lowerDen)
                val upperDiff = kotlin.math.abs(decimal - upperNum.toDouble() / upperDen)
                return if (lowerDiff <= upperDiff) {
                    Pair(lowerNum, lowerDen)
                } else {
                    Pair(upperNum, upperDen)
                }
            }
            
            val midValue = midNum.toDouble() / midDen
            
            when {
                kotlin.math.abs(midValue - decimal) < 0.0001 -> return Pair(midNum, midDen)
                midValue < decimal -> {
                    lowerNum = midNum
                    lowerDen = midDen
                }
                else -> {
                    upperNum = midNum
                    upperDen = midDen
                }
            }
        }
    }
    
    /**
     * Format a dimension value using the display string from API if available,
     * otherwise calculate it locally.
     * 
     * @param value The numeric value
     * @param displayValue The pre-formatted display value from API (optional)
     * @return Formatted dimension string
     */
    fun formatDimension(value: Double?, displayValue: String?): String {
        return displayValue ?: toFractionDisplay(value)
    }
}
