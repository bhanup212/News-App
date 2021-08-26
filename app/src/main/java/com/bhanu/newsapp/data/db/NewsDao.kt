package com.bhanu.newsapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bhanu.newsapp.data.model.local.Article
import io.reactivex.Flowable
import io.reactivex.Observable

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<Article>)

    @Query("SELECT * FROM articles")
    fun getAll(): Flowable<List<Article>>

    @Query("SELECT * FROM articles WHERE id=:id")
    fun getArticleById(id: Int): Observable<Article>

    @Query("DELETE FROM articles")
    fun deleteAll()
}