package com.example.myapplication.vm

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.noteapp.vm.MediaViewModel
import java.lang.IllegalArgumentException

class MediaViewModelFactory(private val application: Application):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MediaViewModel::class.java)) {
            return MediaViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown view model")
    }

}