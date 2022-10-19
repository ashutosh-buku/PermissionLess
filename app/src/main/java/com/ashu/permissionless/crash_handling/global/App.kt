package com.ashu.permissionless.crash_handling.global

import android.app.Application

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        GeneralExceptionHandler.initialize(this, CrashActivity::class.java)
    }
}