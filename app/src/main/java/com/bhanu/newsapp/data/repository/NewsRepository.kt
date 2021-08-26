package com.bhanu.newsapp.data.repository

import com.bhanu.newsapp.data.model.local.Article
import com.bhanu.newsapp.di.NewsService
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewsRepository @Inject constructor(private val newsService: NewsService, private val localRepository: LocalRepository) {

    fun getTopNews(): Flowable<List<Article>> {
        val observable = newsService.getTopNews()
            .subscribeOn(Schedulers.io()).switchMap { data ->
                localRepository.deleteAll()
                localRepository.insertAll(data.toArticleList())
                Observable.just(data)
            }
        try {
            observable.blockingFirst()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return localRepository.getAll()
    }
}
