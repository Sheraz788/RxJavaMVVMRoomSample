package com.example.androidpractices.model

data class PhotosResponse(
    val photoDetail : MutableList<PhotoDetail> = ArrayList()
)