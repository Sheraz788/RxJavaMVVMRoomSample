package com.example.androidpractices.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.androidpractices.model.PhotoDetail

@Dao
interface PhotosDao {
    @Query("Select * From PhotosEntity")
    fun getAllPhotos() : LiveData<MutableList<PhotoDetail>>

    @Insert
    fun insertPhotosData(photosList : MutableList<PhotosEntity>)
}