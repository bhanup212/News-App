package com.bhanu.newsapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bhanu.newsapp.R
import com.bhanu.newsapp.data.model.local.Article
import com.bhanu.newsapp.databinding.NewsItemRowLayoutBinding
import com.bumptech.glide.Glide

class NewsListAdapter(private val onItemClick: (Article) -> Unit) : RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {

    private val newsList: ArrayList<Article> = ArrayList()

    fun updateNewsList(list: ArrayList<Article>) {
        newsList.clear()
        newsList.addAll(list)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NewsItemRowLayoutBinding.inflate(inflater, parent, false)
        return ViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = newsList[position]
        holder.bindData(article)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    class ViewHolder(private val binding: NewsItemRowLayoutBinding, private val onItemClick: (Article) -> Unit) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(article: Article) {
            binding.root.setOnClickListener{
                onItemClick(article)
            }
            binding.source.text = article.sourceName
            binding.date.text = article.publishedAt
            binding.title.text = article.title
            binding.description.text = article.description
            Glide.with(binding.imageView).load(article.urlToImage).placeholder(R.drawable.ic_baseline_menu_book).into(binding.imageView)
        }
    }
}
