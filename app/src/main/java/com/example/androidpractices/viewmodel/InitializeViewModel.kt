package com.example.androidpractices.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidpractices.repository.GetImagesRepository

class InitializeViewModel(val getImagesRepository: GetImagesRepository, val context: Context) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GetImagesViewModel(
            getImagesRepository,
            context
        ) as T
    }
}