package com.example.mvvvmnews.ui.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvvmnews.R
import com.example.mvvvmnews.ui.adapters.NewsAdapter
import com.example.mvvvmnews.ui.ui.NewsActivity
import com.example.mvvvmnews.ui.ui.NewsViewModel
import com.example.mvvvmnews.ui.util.Constants
import com.example.mvvvmnews.ui.util.Constants.Companion.SEARCH_TIME_DELAY
import com.example.mvvvmnews.ui.util.Resource
import kotlinx.android.synthetic.main.fragment_search_news.*
import kotlinx.android.synthetic.main.fragment_search_news.paginationProgressBar
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SearchNewsFragment : Fragment(R.layout.fragment_search_news) {
    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter

    val TAG = "SearchNewsFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel
        setUpRecyclerView()

        //search textBox delay until users type
        var job: Job? = null
        etSearch.addTextChangedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(SEARCH_TIME_DELAY)
                editable?.let {
                    if (editable.toString().isNotEmpty()) {
                        viewModel.searchNews(editable.toString())
                    }
                }
            }
        }

        viewModel.searchNews.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgrssBar()
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }

                is Resource.Error -> {
                    hideProgrssBar()
                    response.message?.let { message ->
                        Log.e(TAG, "Error Occurred: $message")
                    }
                }
                is Resource.Loading -> {
                    showProgrssBar()
                }
            }
        })

    }

    private fun setUpRecyclerView() {
        newsAdapter = NewsAdapter()
        rvSearchNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun hideProgrssBar() {
        paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgrssBar() {
        paginationProgressBar.visibility = View.VISIBLE
    }
}
