package com.mine.notesappsql

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mine.notesappsql.databinding.ActivityUpdateNoteBinding
import java.text.DateFormat
import java.util.Calendar

class UpdateNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateNoteBinding
    private lateinit var db:NoteDataBaseHelper
    private var noteid:Int = -1 // -1 means its empty, its also deal with null pointer exception

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)


        db = NoteDataBaseHelper(this)

        noteid = intent.getIntExtra("note_id",-1)
        if (noteid == -1){
            finish() // its close the current activity
            return
        }

        val note = db.getNotebyID(noteid)

        binding.updateTitle.setText(note.title)
        binding.updateDescription.setText(note.description)

        binding.update.setOnClickListener{

            val calender = Calendar.getInstance().time
            val dateFormat = DateFormat.getDateInstance().format(calender)

            val newTitle = binding.updateTitle.text.toString()
            val newContent = binding.updateDescription.text.toString()

            val updatedNote = Note(noteid,newTitle,newContent,dateFormat.toString())
            db.updateNote(updatedNote)
            finish()
            Toast.makeText(this, "Note is Update !!", Toast.LENGTH_SHORT).show()
        }

    }
}