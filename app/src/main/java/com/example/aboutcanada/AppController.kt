package com.example.aboutcanada

import android.app.Application
import android.content.Context

class AppController : Application() {

    init {
        instance = this
    }

    companion object {
        var instance: Application? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }


}