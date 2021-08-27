package com.bhanu.newsapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bhanu.newsapp.data.model.local.Article
import com.bhanu.newsapp.data.repository.NewsRepository
import com.bhanu.newsapp.di.module.StorageModule
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Named

class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    @Named(StorageModule.IO_SCHEDULER) private val ioScheduler: Scheduler,
    @Named(StorageModule.MAIN_SCHEDULER) private val mainScheduler: Scheduler
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _progressbar = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _progressbar

    private val _newsList = MutableLiveData<ArrayList<Article>>()
    val newsList: LiveData<ArrayList<Article>> = _newsList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    init {
        getTopNews()
    }

    fun getTopNews() {
        compositeDisposable.add(
            newsRepository.getTopNews().observeOn(mainScheduler)
                .subscribeOn(ioScheduler)
                .doOnSubscribe { _progressbar.postValue(true) }
                .doOnError {
                    _errorMessage.postValue(it.message)
                }
                .subscribe(::handleResponse)
        )
    }

    private fun handleResponse(newsList: List<Article>) {
        _newsList.postValue(newsList as ArrayList<Article>)
        _progressbar.postValue(false)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}
