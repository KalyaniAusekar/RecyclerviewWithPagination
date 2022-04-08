package com.example.recyclerviewwithpagination.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.recyclerviewwithpagination.paging.MoviesPaging
import com.example.recyclerviewwithpagination.retrofit.RetrofitApiInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val retrofitApiInterface: RetrofitApiInterface): ViewModel() {

    private val query = MutableLiveData<String>("")

    val moviesList = query.switchMap { query ->
        Pager(PagingConfig(pageSize = 10)) {
            MoviesPaging(query,retrofitApiInterface)
        }.liveData.cachedIn(viewModelScope)
    }

    fun setQuery(s:String){
        query.postValue(s)
    }
}