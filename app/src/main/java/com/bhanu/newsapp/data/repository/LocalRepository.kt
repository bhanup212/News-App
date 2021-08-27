package com.bhanu.newsapp.data.repository

import com.bhanu.newsapp.data.db.NewsDao
import com.bhanu.newsapp.data.model.local.Article
import io.reactivex.Flowable
import io.reactivex.Observable
import javax.inject.Inject

class LocalRepository @Inject constructor(private val dao: NewsDao) {

    fun deleteAll(){
        dao.deleteAll()
    }

    fun insertAll(list: List<Article>){
        dao.insertAll(list)
    }

    fun getAll(): Flowable<List<Article>> {
        return dao.getAll()
    }

    fun getArticleById(id: Int): Observable<Article> {
        return dao.getArticleById(id)
    }
}