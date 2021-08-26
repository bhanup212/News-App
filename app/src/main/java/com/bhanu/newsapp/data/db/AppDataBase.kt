package com.bhanu.newsapp.data.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bhanu.newsapp.data.model.local.Article

@Database(entities = [Article::class], version = 1)
abstract class AppDataBase: RoomDatabase() {
    abstract fun newsDao(): NewsDao

    companion object{
        private lateinit var db: AppDataBase

        fun getDb(application: Application): AppDataBase{
            if (!::db.isInitialized){
                db = Room.databaseBuilder(application, AppDataBase::class.java, "news_dabase").build()
            }
            return db
        }
    }
}