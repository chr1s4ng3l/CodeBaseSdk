package com.tamayo.wireviewer

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * An Android application class that initializes Hilt for dependency injection.
 */
@HiltAndroidApp
class WireApp: Application()