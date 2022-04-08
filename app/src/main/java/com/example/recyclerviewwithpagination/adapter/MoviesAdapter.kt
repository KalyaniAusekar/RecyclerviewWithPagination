package com.example.recyclerviewwithpagination.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewwithpagination.BR
import com.example.recyclerviewwithpagination.databinding.RowMoviesBinding
import com.example.recyclerviewwithpagination.model.Movies

class MoviesAdapter : PagingDataAdapter<Movies,MoviesAdapter.MoviesHolder>(DIFF_UTIL) {

    inner class MoviesHolder(val viewDataBinding : RowMoviesBinding) : RecyclerView.ViewHolder(viewDataBinding.root)

    override fun onBindViewHolder(holder: MoviesAdapter.MoviesHolder, position: Int) {
        holder.viewDataBinding.setVariable(BR.movies,getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesAdapter.MoviesHolder {
        val binding = RowMoviesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MoviesHolder(binding)
    }

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<Movies>() {
            override fun areItemsTheSame(oldItem: Movies, newItem: Movies): Boolean {
                return oldItem.imdbID == newItem.imdbID
            }

            override fun areContentsTheSame(oldItem: Movies, newItem: Movies): Boolean {
                return oldItem == newItem
            }
        }
    }
}