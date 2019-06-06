package com.rogerio.gittestmvvm

import androidx.multidex.MultiDexApplication
import com.facebook.stetho.Stetho

class RogerioXingApplication: MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}