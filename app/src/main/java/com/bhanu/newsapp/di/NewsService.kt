package com.bhanu.newsapp.di

import com.bhanu.newsapp.BuildConfig
import com.bhanu.newsapp.data.model.remote.NewsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("top-headlines")
    fun getTopNews(
        @Query("country") country: String = "in",
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY
    ): Observable<NewsResponse>
}