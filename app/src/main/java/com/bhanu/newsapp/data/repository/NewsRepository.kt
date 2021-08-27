package com.bhanu.newsapp.data.repository

import com.bhanu.newsapp.data.model.local.Article
import com.bhanu.newsapp.di.NewsService
import com.bhanu.newsapp.di.module.StorageModule
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named

class NewsRepository @Inject constructor(
    private val newsService: NewsService,
    private val localRepository: LocalRepository,
    @Named(StorageModule.IO_SCHEDULER) private val ioScheduler: Scheduler
) {

    fun getTopNews(): Flowable<List<Article>> {
        val observable = newsService.getTopNews()
            .subscribeOn(ioScheduler).switchMap { data ->
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
