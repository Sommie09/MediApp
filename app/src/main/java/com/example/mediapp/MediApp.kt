package com.example.mediapp

import android.app.Application
import com.facebook.stetho.Stetho

class MediApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}