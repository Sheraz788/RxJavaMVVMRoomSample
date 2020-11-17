package com.example.androidpractices.services

import com.example.androidpractices.model.PhotoDetail
import retrofit2.http.GET
import io.reactivex.rxjava3.core.Observable

interface ApiServices {

    @GET("photos")
    fun getPhotos() : Observable<MutableList<PhotoDetail> >
}