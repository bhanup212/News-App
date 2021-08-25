package com.bhanu.newsapp

import android.app.Application
import com.bhanu.newsapp.di.AppComponentInitializer

class NewsApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AppComponentInitializer.setApplication(this)
    }
}
