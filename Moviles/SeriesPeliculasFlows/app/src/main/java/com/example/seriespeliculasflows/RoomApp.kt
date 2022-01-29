package com.example.seriespeliculasflows

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RoomApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }

}