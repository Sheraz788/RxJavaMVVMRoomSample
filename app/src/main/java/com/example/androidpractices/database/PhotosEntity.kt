package com.example.androidpractices.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PhotosEntity(
    @PrimaryKey val uid : Int,
    @ColumnInfo(name = "albumId") val albumId : Int,
    @ColumnInfo(name = "title") val title : String,
    @ColumnInfo(name = "url") val url : String,
    @ColumnInfo(name = "thumbnailUrl") val thumbnailUrl : String
)