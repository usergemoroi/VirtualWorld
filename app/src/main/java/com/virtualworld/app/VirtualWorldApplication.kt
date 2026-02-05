package com.virtualworld.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class VirtualWorldApplication : Application() {
    
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: VirtualWorldApplication
            private set
    }
}
