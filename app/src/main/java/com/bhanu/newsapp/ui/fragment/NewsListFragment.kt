package com.bhanu.newsapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bhanu.newsapp.R
import com.bhanu.newsapp.databinding.FragmentNewsListBinding
import com.bhanu.newsapp.di.AppComponentInitializer
import com.bhanu.newsapp.ui.adapter.NewsListAdapter
import com.bhanu.newsapp.ui.viewmodel.NewsViewModel
import javax.inject.Inject

class NewsListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: NewsViewModel by viewModels { viewModelFactory }

    private var _binding: FragmentNewsListBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: NewsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        AppComponentInitializer.getComponent().inject(this)
        _binding = FragmentNewsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        observeViewModel()
    }

    private fun initAdapter() {
        adapter = NewsListAdapter {
            val bundle = Bundle().apply {
                putInt("id", it.id)
            }
            findNavController().navigate(R.id.action_newsListFragment_to_newsDetailsFragment, bundle)
        }
        binding.newsRv.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun observeViewModel() {
        viewModel.isLoading.observe(
            viewLifecycleOwner,
            Observer { isLoading ->
                binding.progressBar.isVisible = isLoading
            }
        )
        viewModel.newsList.observe(
            viewLifecycleOwner,
            Observer { list ->
                adapter.updateNewsList(list)
            }
        )
    }
}
