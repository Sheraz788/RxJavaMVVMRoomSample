package com.example.androidpractices.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.androidpractices.model.PhotoDetail
import com.example.androidpractices.services.ApiServices
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.Exception

class GetImagesDataSource(
    private val apiServices: ApiServices,
    private val compositeDisposable: CompositeDisposable
) {

    private val _networkState = MutableLiveData<NetworkState>() //private networkstate will be used in this class only

    private val _photosResponse = MutableLiveData<MutableList<PhotoDetail> >()

    val networkState : LiveData<NetworkState>
        get() = _networkState

    val photosResponse : LiveData<MutableList<PhotoDetail> >
        get() = _photosResponse


    fun getPhotos(){

        _networkState.postValue(NetworkState.LOADING)

        try {
            compositeDisposable.add(
                apiServices.getPhotos()
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        {
                            _photosResponse.postValue(it)
                            _networkState.postValue(NetworkState.LOADED)
                        },
                        { error ->
                            _networkState.postValue(NetworkState.ERROR)
                        })
            )
        }catch (e : Exception){
            e.printStackTrace()
        }

    }

}