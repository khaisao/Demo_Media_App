package com.example.noteapp.vm

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.MMediaFile
import com.prox.powerpointreader.db.MediaFileDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MediaViewModel(application: Application) : ViewModel() {
    private val noteDao = MediaFileDatabase.getInstance(application).mediaFileDao()
    val notes = noteDao.getAllFile()
    fun addNote(note: MMediaFile) {
        viewModelScope.launch(Dispatchers.IO) {
            noteDao.insertFile(note)
        }
    }
}