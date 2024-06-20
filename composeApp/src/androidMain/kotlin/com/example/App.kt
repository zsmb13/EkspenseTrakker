package com.example

import android.app.Application
import createDataStore
import getDatabaseBuilder
import initKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin(
            databaseBuilder = getDatabaseBuilder(this),
            dataStore = createDataStore(this),
        )
    }
}
