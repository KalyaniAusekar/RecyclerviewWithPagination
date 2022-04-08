package com.example.recyclerviewwithpagination.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.recyclerviewwithpagination.model.Movies
import com.example.recyclerviewwithpagination.model.NetworkMovieResponse
import com.example.recyclerviewwithpagination.retrofit.RetrofitApiInterface
import com.example.recyclerviewwithpagination.utils.Constants

class MoviesPaging(private val s:String, private val retrofitApiInterface: RetrofitApiInterface) : PagingSource<Int, Movies>() {

    override fun getRefreshKey(state: PagingState<Int, Movies>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state?.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1)?:anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movies> {
        val page = params.key?:1

        return try {
            val data = retrofitApiInterface.getAllMovies(s,page,Constants.API_KEY)

            LoadResult.Page(
                data = data.body()?.Search!!, // return movies list
                prevKey = if(page == 1) null  else  page-1,
                nextKey = if(data.body()?.Search?.isEmpty()!!)null else page+1
            )

        }catch (e:Exception) {
            LoadResult.Error(e)
        }
    }
}