package com.example.myapplication

import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMain2Binding
import com.example.myapplication.model.MMediaFile
import java.io.File

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding:ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val item = intent.getSerializableExtra("file") as MMediaFile
        val file = File(item.absolutePath)
       val uri = Uri.fromFile(file)

        val simpleVideoView =binding.vv
        val mediaController = MediaController(this)
        mediaController.setAnchorView(simpleVideoView);
        simpleVideoView.setMediaController(mediaController)
        simpleVideoView.setVideoURI(uri);
        simpleVideoView.start();




    }
}