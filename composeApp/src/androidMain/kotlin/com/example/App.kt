package com.example

import android.app.Application
import getDatabaseBuilder
import initKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin(databaseBuilder = getDatabaseBuilder(this))
    }
}
