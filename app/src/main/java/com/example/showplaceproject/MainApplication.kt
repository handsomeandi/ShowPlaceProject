package com.example.showplaceproject

import android.app.Application
import com.yandex.mapkit.MapKitFactory
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
//        MapKitFactory.setApiKey("b1394d32-90a5-4fa2-a2ff-da4a5e9005e1")
        MapKitFactory.setApiKey("32eb2b2a-d6c7-4f97-ac00-1aebf0f4b638")
    }
}