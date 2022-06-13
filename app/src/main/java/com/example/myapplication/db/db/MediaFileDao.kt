package com.prox.powerpointreader.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapplication.model.MMediaFile

@Dao
interface MediaFileDao {

    @Query("Select * from MMediaFile")
    fun getAllFile(): LiveData<List<MMediaFile>>
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertFile(mediaFile: MMediaFile):Long
}