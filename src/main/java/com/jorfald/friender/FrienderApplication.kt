package com.jorfald.friender

import android.app.Application

class FrienderApplication : Application() {

    companion object {
        lateinit var application: Application
    }

    override fun onCreate() {
        super.onCreate()

        application = this
    }
}