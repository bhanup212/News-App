package com.bhanu.newsapp.data.repository

import com.bhanu.newsapp.data.model.remote.NewsResponse
import com.bhanu.newsapp.di.NewsService
import io.reactivex.Observable
import javax.inject.Inject

class NewsRepository @Inject constructor(private val newsService: NewsService){

    fun getTopNews(): Observable<NewsResponse> {
        return newsService.getTopNews()
    }
}