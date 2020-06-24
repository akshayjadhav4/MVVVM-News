package com.example.mvvvmnews.ui.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvvmnews.R
import com.example.mvvvmnews.ui.adapters.NewsAdapter
import com.example.mvvvmnews.ui.ui.NewsActivity
import com.example.mvvvmnews.ui.ui.NewsViewModel
import com.example.mvvvmnews.ui.util.Resource
import kotlinx.android.synthetic.main.fragment_braking_news.*


class BrakingNewsFragment : Fragment(R.layout.fragment_braking_news) {

    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter

    val TAG = "BrakingNewsFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel
        setUpRecyclerView()

        viewModel.breakingNews.observe(viewLifecycleOwner, Observer { response ->
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
                is Resource.Loading ->{
                    showProgrssBar()
                }
            }
        })
    }

    private fun setUpRecyclerView() {
        newsAdapter = NewsAdapter()
        rvBreakingNews.apply {
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
