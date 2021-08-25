package com.bhanu.newsapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bhanu.newsapp.data.model.local.Article
import com.bhanu.newsapp.data.model.remote.NewsResponse
import com.bhanu.newsapp.data.repository.NewsRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _progressbar = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _progressbar

    private val _newsList = MutableLiveData<Article>()
    val newsList: LiveData<Article> = _newsList

    fun getTopNews() {
        compositeDisposable.add(
            newsRepository.getTopNews().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe { _progressbar.postValue(true) }
                .switchMap { data ->
                    {

                    }
                    Observable.just(data)
                }
                .subscribe(::handleResponse)
        )
    }

    private fun handleResponse(newsResponse: NewsResponse) {
        _progressbar.postValue(false)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}
