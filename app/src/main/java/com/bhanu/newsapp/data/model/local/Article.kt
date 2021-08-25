package com.bhanu.newsapp.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")
data class Article(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String?,
    val sourceId: String?,
    val sourceName: String?
)
