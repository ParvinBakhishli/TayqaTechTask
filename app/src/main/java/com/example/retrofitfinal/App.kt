package com.example.retrofitfinal

import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        codeCacheDir.setReadOnly()
    }

}
