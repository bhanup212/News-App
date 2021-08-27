package com.bhanu.newsapp.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bhanu.newsapp.R
import com.bhanu.newsapp.data.model.local.Article
import com.bhanu.newsapp.databinding.FragmentNewsDetailsBinding
import com.bhanu.newsapp.di.AppComponentInitializer
import com.bhanu.newsapp.ui.viewmodel.NewsDetailsViewModel
import com.bumptech.glide.Glide
import javax.inject.Inject

class NewsDetailsFragment : Fragment() {

    private var _binding: FragmentNewsDetailsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: NewsDetailsViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        AppComponentInitializer.getComponent().inject(this)
        _binding = FragmentNewsDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments?.getInt("id")
        if (id != null) viewModel.getArticleById(id)
        observeViewModel()
        clickListeners()
    }

    private fun observeViewModel() {
        viewModel.article.observe(
            viewLifecycleOwner,
            Observer { article ->
                updateArticle(article)
            }
        )
    }

    private fun clickListeners() {
        binding.arrowBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun updateArticle(article: Article) {
        binding.title.text = article.title
        binding.content.text = article.content
        binding.description.text = article.description
        binding.date.text = article.publishedAt
        binding.source.text = article.sourceName

        Glide.with(binding.imageView).load(article.urlToImage).placeholder(R.drawable.ic_baseline_menu_book).into(
            binding.imageView
        )
        binding.visitLink.setOnClickListener {
            openLink(article.url)
        }
    }

    private fun openLink(url: String) {
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }
}
