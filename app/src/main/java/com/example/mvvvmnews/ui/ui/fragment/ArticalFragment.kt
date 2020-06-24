package com.example.mvvvmnews.ui.ui.fragment

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.mvvvmnews.R
import com.example.mvvvmnews.ui.ui.NewsActivity
import com.example.mvvvmnews.ui.ui.NewsViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_artical.*

class ArticalFragment : Fragment(R.layout.fragment_artical) {
    lateinit var viewModel: NewsViewModel
    //get args as Global variables
    val args: ArticalFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel

        val article = args.article
        webView.apply {
            webViewClient = WebViewClient()
            loadUrl(article.url)
        }

        //fab button
        fab.setOnClickListener {
            viewModel.saveArticle(article)
            //snackbar
            Snackbar.make(view,"Article Saved Successfully.",Snackbar.LENGTH_SHORT).show()
        }
    }
}
