package com.bhanu.newsapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bhanu.newsapp.R
import kotlinx.coroutines.delay

class SplashFragment : Fragment() {

    companion object {
        private const val SPLASH_DELAY = 2000L
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigateToNewsScreen()
    }

    private fun navigateToNewsScreen() {
        lifecycleScope.launchWhenStarted {
            delay(SPLASH_DELAY)
            findNavController().navigate(R.id.action_splashFragment_to_newsListFragment)
        }
    }
}
