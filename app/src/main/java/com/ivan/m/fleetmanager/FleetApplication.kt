package com.ivan.m.fleetmanager

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Passing the hilt the application context.
 */
@HiltAndroidApp
class FleetApplication : Application()