package com.example.noteapp.adapter.diff

import androidx.recyclerview.widget.DiffUtil
import com.example.myapplication.model.MMediaFile

object MediaItemCallBack : DiffUtil.ItemCallback<MMediaFile>() {
    override fun areItemsTheSame(oldItem: MMediaFile, newItem: MMediaFile): Boolean {
        return oldItem.absolutePath == newItem.absolutePath
    }

    override fun areContentsTheSame(oldItem: MMediaFile, newItem: MMediaFile): Boolean {
        return oldItem == newItem
    }
}