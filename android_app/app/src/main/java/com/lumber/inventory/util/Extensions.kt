package com.lumber.inventory.util

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

/**
 * Kotlin extension functions for common operations.
 */

/**
 * Format an ISO date string to a user-friendly display format.
 */
fun String?.formatDate(): String {
    if (this.isNullOrBlank()) return ""
    return try {
        val instant = Instant.parse(this)
        val formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)
            .withZone(ZoneId.systemDefault())
        formatter.format(instant)
    } catch (e: Exception) {
        this
    }
}

/**
 * Capitalize the first letter of a string.
 */
fun String.capitalizeFirst(): String {
    return this.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
}

/**
 * Truncate a string to a maximum length, adding ellipsis if needed.
 */
fun String.truncate(maxLength: Int): String {
    return if (this.length <= maxLength) this else "${this.take(maxLength - 1)}…"
}
