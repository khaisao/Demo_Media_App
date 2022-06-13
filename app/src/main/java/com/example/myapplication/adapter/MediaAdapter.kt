package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.callback.ItemClick
import com.example.myapplication.databinding.ItemLayoutBinding
import com.example.myapplication.model.MMediaFile
import com.example.noteapp.adapter.diff.MediaItemCallBack

class MediaAdapter(private val callback:ItemClick) : ListAdapter<MMediaFile, MediaAdapter.ViewHolder>(MediaItemCallBack) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(it)
        }
    }



    inner class ViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(MMediaFile: MMediaFile ) {
            binding.tvTitle.text=MMediaFile.title
            binding.tvTitle.setOnClickListener {
                callback.onClick(MMediaFile)
            }
        }
    }
}