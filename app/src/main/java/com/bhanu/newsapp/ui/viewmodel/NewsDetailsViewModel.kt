package com.bhanu.newsapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bhanu.newsapp.data.model.local.Article
import com.bhanu.newsapp.data.repository.LocalRepository
import com.bhanu.newsapp.di.module.StorageModule
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import javax.inject.Named

class NewsDetailsViewModel @Inject constructor(
    private val localRepository: LocalRepository,
    @Named(StorageModule.IO_SCHEDULER) private val ioScheduler: Scheduler,
    @Named(StorageModule.MAIN_SCHEDULER) private val mainScheduler: Scheduler
) :
    ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _article = MutableLiveData<Article>()
    val article: LiveData<Article> = _article

    fun getArticleById(id: Int) {
        compositeDisposable.add(
            localRepository.getArticleById(id).observeOn(mainScheduler)
                .subscribeOn(ioScheduler)
                .subscribe(::handleResponse)
        )
    }

    private fun handleResponse(article: Article) {
        _article.value = article
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}
