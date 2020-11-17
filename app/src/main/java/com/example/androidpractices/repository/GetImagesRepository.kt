package com.example.androidpractices.repository

import androidx.lifecycle.LiveData
import com.example.androidpractices.model.PhotoDetail
import com.example.androidpractices.model.PhotosResponse
import com.example.androidpractices.services.ApiServices
import io.reactivex.rxjava3.disposables.CompositeDisposable


class GetImagesRepository (
    private val apiServices: ApiServices
){

    lateinit var getImagesDataSource: GetImagesDataSource

    fun getPhotos(
        compositeDisposable: CompositeDisposable
    ) : LiveData<MutableList<PhotoDetail> >{

        getImagesDataSource =
            GetImagesDataSource(
                apiServices,
                compositeDisposable
            )

        getImagesDataSource.getPhotos()

        return  getImagesDataSource.photosResponse
    }


    fun getNetworkState() : LiveData<NetworkState> {
        return getImagesDataSource.networkState
    }

}