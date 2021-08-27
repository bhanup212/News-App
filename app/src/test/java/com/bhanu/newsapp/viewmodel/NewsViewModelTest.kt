package com.bhanu.newsapp.viewmodel

import com.bhanu.newsapp.data.repository.NewsRepository
import com.bhanu.newsapp.ui.viewmodel.NewsViewModel
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class NewsViewModelTest {

    private lateinit var viewModel: NewsViewModel

    @Mock
    private lateinit var newsRepository: NewsRepository

    @Before
    fun setUp(){
        MockitoAnnotations.openMocks(this)
        val testScheduler = TestScheduler()
        viewModel = NewsViewModel(newsRepository, testScheduler, testScheduler)
    }

    @After
    fun tearDown(){

    }

    @Test
    fun test(){
        viewModel.getTopNews()
    }
}