package com.training.coroutinepackdemo.ui.newsList.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.training.coroutinepackdemo.data.local.dao.NewsDaoImpl
import com.training.coroutinepackdemo.data.local.entity.NewsEntity
import com.training.coroutinepackdemo.data.repository.NewsListRepository
import com.training.coroutinepackdemo.utils.NetworkHelper
import com.training.coroutinepackdemo.utils.Resource
import kotlinx.coroutines.launch
import java.lang.Exception

class NewsListViewModel(
    private var newsListRepository: NewsListRepository,
    private val databaseHelper: NewsDaoImpl,
    private var networkHelper: NetworkHelper
): ViewModel() {

    private var newsList= MutableLiveData<Resource<List<NewsEntity>>>()
    private var totalNewsList= mutableListOf<NewsEntity>()
    private var  PAGE_NO:Int=1
    private  var loading=true

    init{
        fetchNews()
    }

    private fun fetchNews() {
        viewModelScope.launch {
            newsList.postValue(Resource.loading(data = null))
            try {
                val newsToInsertInDB = newsListRepository.getNews(PAGE_NO.toString())
                if(networkHelper.isNetworkConnected()){
                    databaseHelper.insertAll(newsToInsertInDB)
                    totalNewsList.addAll(newsToInsertInDB)
                }
                if(totalNewsList.size>0){
                    loading=false
                    newsList.postValue(Resource.success(newsToInsertInDB))
                }else{
                    newsList.postValue(Resource.error(msg = "No Record Found",data = null))
                }

            }catch (e:Exception){
                newsList.postValue(Resource.error(msg = "Something went wrong,Please try again",data = null))
            }
        }
    }

     fun getNews(): LiveData<Resource<List<NewsEntity>>> {
        return newsList
    }

    fun onLoadMore(): LiveData<Resource<List<NewsEntity>>> {
        PAGE_NO++
        fetchNews()
        return newsList
    }

}
