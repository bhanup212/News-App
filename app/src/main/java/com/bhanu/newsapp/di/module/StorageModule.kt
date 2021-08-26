package com.bhanu.newsapp.di.module

import android.app.Application
import com.bhanu.newsapp.data.db.AppDataBase
import com.bhanu.newsapp.data.db.NewsDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StorageModule(private val application: Application) {

    @Singleton
    @Provides
    fun provideApplication(): Application{
        return application
    }

    @Singleton
    @Provides
    fun provideAppDb(application: Application): AppDataBase{
        return AppDataBase.getDb(application)
    }

    @Singleton
    @Provides
    fun provideNewsDao(appDataBase: AppDataBase): NewsDao{
        return appDataBase.newsDao()
    }
}