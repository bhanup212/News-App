package com.bhanu.newsapp.data.model.remote

import com.bhanu.newsapp.data.model.local.Article
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat

data class NewsResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResult")
    val totalResult: Int,
    @SerializedName("articles")
    val articles: ArrayList<Article>
) {
    data class Article(
        @SerializedName("source")
        val source: Source,
        @SerializedName("author")
        val author: String?,
        @SerializedName("title")
        val title: String?,
        @SerializedName("description")
        val description: String?,
        @SerializedName("url")
        val url: String,
        @SerializedName("urlToImage")
        val urlToImage: String?,
        @SerializedName("publishedAt")
        val publishedAt: String,
        @SerializedName("content")
        val content: String?
    ) {
        data class Source(
            @SerializedName("id")
            val id: String?,
            @SerializedName("name")
            val name: String?
        )

        fun toArticle(): com.bhanu.newsapp.data.model.local.Article {
            return Article(
                0,
                this.author,
                this.title,
                this.description,
                this.url,
                this.urlToImage,
                this.getFormattedData(),
                this.content,
                this.source.id,
                this.source.name
            )
        }

        private fun getFormattedData(): String{
            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
            val outSdf = SimpleDateFormat("EEE dd MMM yyyy")
            val date = sdf.parse(this.publishedAt)
            return outSdf.format(date)
        }
    }

    fun toArticleList(): ArrayList<com.bhanu.newsapp.data.model.local.Article>{
        val list = ArrayList<com.bhanu.newsapp.data.model.local.Article>()
        articles.forEach { a ->
            list.add(a.toArticle())
        }
        return list
    }
}
