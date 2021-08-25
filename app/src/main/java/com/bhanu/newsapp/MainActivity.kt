package com.bhanu.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bhanu.newsapp.di.DaggerAppComponent
import com.bhanu.newsapp.di.module.StorageModule

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}