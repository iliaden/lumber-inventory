package com.lumber.inventory

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Application class for Lumber Inventory.
 * Annotated with @HiltAndroidApp to enable Hilt dependency injection.
 */
@HiltAndroidApp
class LumberInventoryApp : Application()
