package com.training.coroutinepackdemo.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.training.coroutinepackdemo.data.api.ApiHelper
import com.training.coroutinepackdemo.data.local.dao.NewsDaoImpl
import com.training.coroutinepackdemo.data.repository.NewsListRepositoryImpl
import com.training.coroutinepackdemo.ui.newsList.viewModel.NewsListViewModel
import com.training.coroutinepackdemo.utils.NetworkHelper
import java.lang.IllegalArgumentException

class ViewModelfactory(private  val apiHelper: ApiHelper,private val networkHelper: NetworkHelper, private val databaseHelper: NewsDaoImpl): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(NewsListViewModel::class.java)){
            return  NewsListViewModel(NewsListRepositoryImpl(apiHelper,networkHelper,databaseHelper),databaseHelper,networkHelper) as T
        }else{
            throw IllegalArgumentException("Unknown class exception")
        }
    }

}
