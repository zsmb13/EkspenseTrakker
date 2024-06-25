package com.example

import android.app.Application
import createDataStore
import createDatabaseBuilder
import initKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin(
            databaseBuilder = createDatabaseBuilder(this),
            dataStore = createDataStore(this),
        )
    }
}
