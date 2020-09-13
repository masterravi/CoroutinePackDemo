package com.training.coroutinepackdemo.ui.newsList.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.training.coroutinepackdemo.data.local.dao.NewsDaoImpl
import com.training.coroutinepackdemo.data.local.entity.NewsEntity
import com.training.coroutinepackdemo.data.repository.NewsListRepository
import com.training.coroutinepackdemo.utils.Resource
import kotlinx.coroutines.launch
import java.lang.Exception

class NewsListViewModel(private var newsListRepository: NewsListRepository, private val databaseHelper: NewsDaoImpl): ViewModel() {

    private var newsList= MutableLiveData<Resource<List<NewsEntity>>>()

    init{
        fetchNews()
    }

    private fun fetchNews() {
        viewModelScope.launch {
            newsList.postValue(Resource.loading(data = null))
            try {
                val newsToInsertInDB = newsListRepository.getNews("1")

                databaseHelper.insertAll(newsToInsertInDB)

                newsList.postValue(Resource.success(newsToInsertInDB))
            }catch (e:Exception){
                newsList.postValue(Resource.error(msg =e.toString(),data = null))
            }
        }
    }

     fun getNews(): LiveData<Resource<List<NewsEntity>>> {
        return newsList
    }

}