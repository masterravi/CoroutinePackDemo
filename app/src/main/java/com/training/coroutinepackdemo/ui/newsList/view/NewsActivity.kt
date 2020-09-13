package com.training.coroutinepackdemo.ui.newsList.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
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
    private lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_main)
        setupUI()
        setupViewModel()
        setupObserver()
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = NewsAdapter(arrayListOf())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
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
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(newsList: List<NewsEntity>) {
        adapter.addData(newsList)
        adapter.notifyDataSetChanged()
    }

    private fun setupViewModel() {

        newsListViewModel = ViewModelProviders.of(
            this,
            ViewModelfactory(ApiHelperImpl(RequestBuilder.apiService), NetworkHelper(this),NewsDaoImpl(
                DatabaseBuilder.getInstance(applicationContext)))
        ).get(NewsListViewModel::class.java)
    }
}
