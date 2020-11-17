package com.example.androidpractices.services

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import com.example.androidpractices.services.ApiServices

class RetrofitBuilder {

    companion object {
        val baseURL = "https://jsonplaceholder.typicode.com/"

        private val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()


        fun apiService() : ApiServices {
            return retrofit.create(
                ApiServices::class.java)
        }
    }


}