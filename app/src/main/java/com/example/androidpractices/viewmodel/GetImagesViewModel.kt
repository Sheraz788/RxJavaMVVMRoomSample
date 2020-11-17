package com.example.androidpractices.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.androidpractices.database.AppDatabase
import com.example.androidpractices.model.PhotoDetail
import com.example.androidpractices.model.PhotosResponse
import com.example.androidpractices.repository.GetImagesRepository
import com.example.androidpractices.repository.NetworkState
import io.reactivex.rxjava3.disposables.CompositeDisposable

class GetImagesViewModel (
    private val getImagesRepository: GetImagesRepository,
    private val context : Context
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    //this will be used to get photos list from remote server
    val photosResponse : LiveData<MutableList<PhotoDetail> > by lazy {
        getImagesRepository.getPhotos(compositeDisposable)
    }

    val getNetworkState : LiveData<NetworkState> by lazy {
        getImagesRepository.getNetworkState()
    }

    //this will be used to get photos list from local database
    val photosList : LiveData<MutableList<PhotoDetail>> by lazy {
        AppDatabase.getAppDatabase(context).photosDao().getAllPhotos()
    }
}