package com.example.recyclerviewwithpagination.model

import com.google.gson.annotations.SerializedName

data class NetworkMovieResponse(
    @SerializedName("Search")
    var Search: ArrayList<Movies> = arrayListOf(),
    @SerializedName("totalResults")
    var totalResults: String? = null,
    @SerializedName("Response")
    var Response: String? = null
)
