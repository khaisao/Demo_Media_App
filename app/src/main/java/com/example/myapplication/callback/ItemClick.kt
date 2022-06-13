package com.example.myapplication.callback

import com.example.myapplication.model.MMediaFile

interface ItemClick {
    fun onClick(mediaFile: MMediaFile)
}