package com.example.recyclerviewwithpagination.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.recyclerview.widget.GridLayoutManager
import com.example.recyclerviewwithpagination.R
import com.example.recyclerviewwithpagination.adapter.MoviesAdapter
import com.example.recyclerviewwithpagination.databinding.FragmentMoviesBinding
import com.example.recyclerviewwithpagination.viewModel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : Fragment() {

    lateinit var binding : FragmentMoviesBinding
    private val moviesViewModel : MoviesViewModel by viewModels()

    private val moviesAdapter = MoviesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentMoviesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        setRecyclerMovies()

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    moviesViewModel.setQuery(it)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        moviesViewModel.moviesList.observe(viewLifecycleOwner){
            moviesAdapter.submitData(lifecycle,it)
        }
    }

    private fun setRecyclerMovies() {
        binding.recyclerMovies.apply {
            adapter = moviesAdapter
            layoutManager = GridLayoutManager(requireContext(),2)
        }
    }
}