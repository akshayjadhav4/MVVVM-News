package com.example.mvvvmnews.ui.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvvmnews.R
import com.example.mvvvmnews.ui.adapters.NewsAdapter
import com.example.mvvvmnews.ui.ui.NewsActivity
import com.example.mvvvmnews.ui.ui.NewsViewModel
import kotlinx.android.synthetic.main.fragment_saved_news.*
import kotlinx.android.synthetic.main.fragment_search_news.*


class SavedNewsFragment : Fragment(R.layout.fragment_saved_news) {
    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter

    val TAG = "SavedNewsFragment"
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel
        setUpRecyclerView()


        //to open webView
        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article",it)
            }
            findNavController().navigate(R.id.action_savedNewsFragment_to_articleFragment,bundle)
        }

        viewModel.getSaveNews().observe(viewLifecycleOwner, Observer { articles ->
            newsAdapter.differ.submitList(articles)
        })
    }

    private fun setUpRecyclerView() {
        newsAdapter = NewsAdapter()
        rvSavedNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}
