package com.example.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.adapter.MediaAdapter
import com.example.myapplication.callback.ItemClick
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.model.MMediaFile
import com.example.myapplication.vm.MediaViewModelFactory
import com.example.noteapp.vm.MediaViewModel
import java.io.File

class MainActivity : AppCompatActivity(),ItemClick {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MediaAdapter
    private val viewModel: MediaViewModel by viewModels {
        MediaViewModelFactory(application)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        adapter = MediaAdapter(this)
        binding.rv.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rv.adapter = adapter
        getPptxList()
        viewModel.notes.observe(this) {
            adapter.submitList(it)
        }
    }
    private fun getPptxList() {
        val projection = arrayOf(
            MediaStore.Files.FileColumns._ID,
            MediaStore.Files.FileColumns.DATA,
            MediaStore.Files.FileColumns.DATE_ADDED,
            MediaStore.Files.FileColumns.MEDIA_TYPE,
            MediaStore.Files.FileColumns.MIME_TYPE,
            MediaStore.Files.FileColumns.TITLE
        )
        val selection = (MediaStore.Files.FileColumns.MEDIA_TYPE + "="
                + MediaStore.Files.FileColumns.MEDIA_TYPE_AUDIO
                + " OR "
                + MediaStore.Files.FileColumns.MEDIA_TYPE + "="
                + MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO)
        val collection: Uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MediaStore.Files.getContentUri(MediaStore.VOLUME_EXTERNAL)
        } else {
            MediaStore.Files.getContentUri("external")
        }
        contentResolver.query(collection, projection, selection, null, null)
            .use { cursor ->
                assert(cursor != null)
                if (cursor!!.moveToFirst()) {
                    val columnData = cursor.getColumnIndex(MediaStore.Files.FileColumns.DATA)
                    val title = cursor.getColumnIndex(MediaStore.Files.FileColumns.TITLE)
                    do {
                        val absoluteFile = cursor.getString(columnData)
                        val asgasg = cursor.getString(title)
                        Log.d("asgawg", "getPptxList: ")
                        val file = File(absoluteFile)
                        if (file.exists()) {
                            viewModel.addNote(
                                MMediaFile(
                                    absoluteFile,
                                    asgasg,
                                )
                            )
                        }
                    } while (cursor.moveToNext())
                }
            }
    }

    override fun onClick(mediaFile: MMediaFile) {
        val intent = Intent(this,MainActivity2::class.java)
        intent.putExtra("file",mediaFile)
        startActivity(intent)
    }
}