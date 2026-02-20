package com.openclassroom.eggtracker

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class EggTrackerApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialisation de Hilt
    }
}