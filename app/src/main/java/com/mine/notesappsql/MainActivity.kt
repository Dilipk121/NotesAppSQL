package com.mine.notesappsql

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mine.notesappsql.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db:NoteDataBaseHelper
    private lateinit var notesAdapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NoteDataBaseHelper(this)
        notesAdapter = NotesAdapter(db.getAllNotes(),this)

        //add values to recycle views
        binding.recyclerView.adapter = notesAdapter

        binding.floatingActionButton.setOnClickListener{

            startActivity(Intent(this,AddNoteActivity::class.java))

        }
    }

    override fun onResume() {
        super.onResume()
        notesAdapter.refreshNotes(db.getAllNotes())
    }
}