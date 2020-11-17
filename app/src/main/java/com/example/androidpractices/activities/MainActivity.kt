package com.example.androidpractices.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.orendaandroidtask.R
import com.example.androidpractices.adapter.PhotosAdapter
import com.example.androidpractices.database.AppDatabase
import com.example.androidpractices.database.PhotosEntity
import com.example.androidpractices.model.PhotoDetail
import com.example.androidpractices.repository.GetImagesRepository
import com.example.androidpractices.repository.NetworkState
import com.example.androidpractices.services.RetrofitBuilder
import com.example.androidpractices.viewmodel.GetImagesViewModel
import com.example.androidpractices.viewmodel.InitializeViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var getImagesRepository: GetImagesRepository
    lateinit var getImagesViewModel: GetImagesViewModel
    lateinit var photosAdapter: PhotosAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //getting data from database at first if there is no data return then it will call from network
        triggerGetPhotosFromLocalDatabase()
    }

    //it will initialize the view model and observers to fetch data from network
    private fun triggerGetPhotos(){
        val apiServices = RetrofitBuilder.apiService()
        getImagesRepository =
            GetImagesRepository(
                apiServices
            )

        getImagesViewModel = getImagesViewModel()

        subscribeObservers()
    }

    //getting data from local database
    private fun triggerGetPhotosFromLocalDatabase(){
        val apiServices = RetrofitBuilder.apiService()
        getImagesRepository =
            GetImagesRepository(
                apiServices
            )
        getImagesViewModel = getImagesViewModel()

        subscribeObserversForLocalData()
    }

    //this function will initialize the view model which then will be resposible for network call
    private fun getImagesViewModel() : GetImagesViewModel {
        return InitializeViewModel(
            getImagesRepository,
            this
        ).create(GetImagesViewModel::class.java)
    }


    //this function will be responsible for observing the data returned from network
    private fun subscribeObservers(){

        getImagesViewModel.photosResponse.observe(this, Observer {

            val photosList : MutableList<PhotosEntity> = ArrayList()
            for (photoDetail in it){
                val photosEntity =
                    PhotosEntity(
                        photoDetail.id!!,
                        photoDetail.albumId!!,
                        photoDetail.title!!,
                        photoDetail.url!!,
                        photoDetail.thumbnailUrl!!
                    )
                photosList.add(photosEntity)
            }
            //storing the data to database on using another thread
            Thread {
                AppDatabase.getAppDatabase(this).photosDao().insertPhotosData(photosList)
            }.start()

            settingImagesRecyclerViewAdapter(it)
        })

        getImagesViewModel.getNetworkState.observe(this, Observer {
            if (it == NetworkState.LOADING){
                progress_bar.visibility = View.VISIBLE
            }else if (it == NetworkState.ERROR){
                progress_bar.visibility = View.GONE
                Toast.makeText(this, "${it.msg}", Toast.LENGTH_SHORT).show()
            }else{
                progress_bar.visibility = View.GONE
            }
        })
    }

    //subscribing observer for getting data from room database
    private fun subscribeObserversForLocalData(){
        getImagesViewModel.photosList.observe(this, Observer {
            if (it.size == 0){
                triggerGetPhotos()
            }else {
                settingImagesRecyclerViewAdapter(it)
            }
        })
    }

    //setting up recycler view adapter
    private fun settingImagesRecyclerViewAdapter(photoDetail : MutableList<PhotoDetail> ){
        photosAdapter =
            PhotosAdapter(photoDetail, this)
        images_recycler_view.layoutManager = LinearLayoutManager(this)
        images_recycler_view.adapter = photosAdapter
    }

}