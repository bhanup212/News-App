package com.bhanu.newsapp.di.module

import android.app.Application
import com.bhanu.newsapp.data.db.AppDataBase
import com.bhanu.newsapp.data.db.NewsDao
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

@Module
class StorageModule(private val application: Application) {

    @Singleton
    @Provides
    fun provideApplication(): Application {
        return application
    }

    @Singleton
    @Provides
    fun provideAppDb(application: Application): AppDataBase {
        return AppDataBase.getDb(application)
    }

    @Singleton
    @Provides
    fun provideNewsDao(appDataBase: AppDataBase): NewsDao {
        return appDataBase.newsDao()
    }

    @Named(IO_SCHEDULER)
    @Provides
    fun provideIoScheduler(): Scheduler {
        return Schedulers.io()
    }

    @Named(MAIN_SCHEDULER)
    @Provides
    fun provideMainScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    companion object {
        const val IO_SCHEDULER = "IO_SCHEDULER"
        const val MAIN_SCHEDULER = "MAIN_SCHEDULER"
    }
}
