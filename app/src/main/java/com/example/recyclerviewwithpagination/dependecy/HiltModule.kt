package com.example.recyclerviewwithpagination.dependecy

import com.example.recyclerviewwithpagination.retrofit.RetrofitApiInterface
import com.example.recyclerviewwithpagination.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object HiltModule {

    @Provides
    fun provideRetrofitInterface() : RetrofitApiInterface {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(
            GsonConverterFactory.create()
        ).build().create(RetrofitApiInterface::class.java)
    }
}