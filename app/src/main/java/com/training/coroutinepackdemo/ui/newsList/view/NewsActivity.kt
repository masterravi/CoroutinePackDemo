package com.training.coroutinepackdemo.ui.newsList.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.training.coroutinepackdemo.R
import com.training.coroutinepackdemo.data.api.ApiHelperImpl
import com.training.coroutinepackdemo.data.api.RequestBuilder
import com.training.coroutinepackdemo.data.local.DatabaseBuilder
import com.training.coroutinepackdemo.data.local.dao.NewsDaoImpl
import com.training.coroutinepackdemo.data.local.entity.NewsEntity
import com.training.coroutinepackdemo.ui.base.ViewModelfactory
import com.training.coroutinepackdemo.ui.newsList.adapter.NewsAdapter
import com.training.coroutinepackdemo.ui.newsList.viewModel.NewsListViewModel
import com.training.coroutinepackdemo.utils.NetworkHelper
import com.training.coroutinepackdemo.utils.Status
import kotlinx.android.synthetic.main.activity_news_main.*

class NewsActivity : AppCompatActivity() {

    private lateinit var newsListViewModel: NewsListViewModel
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_main)
        setupUI()
        setupViewModel()
        setupObserver()
    }

    private fun setupUI() {
        newsAdapter= NewsAdapter(arrayListOf())
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = newsAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    layoutManager?.run {
                        if (this is LinearLayoutManager
                            && itemCount > 0
                            && itemCount == findLastVisibleItemPosition() + 1
                        ) newsListViewModel.onLoadMore()
                    }
                }
            })
        }
    }

    private fun setupObserver() {
        newsListViewModel.getNews().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressbar.visibility = View.GONE
                    it.data?.let { newsList -> renderList(newsList) }
                    recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    progressbar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    progressbar.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
                    Toast.makeText(this, "Something went wrong,please try again", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(newsList: List<NewsEntity>) {
        newsAdapter.addData(newsList)
        newsAdapter.notifyDataSetChanged()
    }

    private fun setupViewModel() {

        newsListViewModel = ViewModelProviders.of(
            this,
            ViewModelfactory(ApiHelperImpl(RequestBuilder.apiService), NetworkHelper(this),NewsDaoImpl(
                DatabaseBuilder.getInstance(applicationContext)))
        ).get(NewsListViewModel::class.java)
    }
}
