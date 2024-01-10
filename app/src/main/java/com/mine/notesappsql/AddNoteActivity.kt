package com.mine.notesappsql

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mine.notesappsql.databinding.ActivityAddNoteBinding
import java.text.DateFormat
import java.util.Calendar

class AddNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding
    private lateinit var db: NoteDataBaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NoteDataBaseHelper(this)

        binding.done.setOnClickListener{

            val calender = Calendar.getInstance().time
            val dateFormat = DateFormat.getDateInstance().format(calender)

            val title = binding.etTitle.text.toString()
            val content = binding.etDescription.text.toString()
            val date = dateFormat.toString()

            if(title.isEmpty()){
                Toast.makeText(this, "!! Please Fill Title", Toast.LENGTH_SHORT).show()
            }
            else if(content.isEmpty()){
                Toast.makeText(this, "!! Please Fill Content", Toast.LENGTH_SHORT).show()
            }
            else{
                val note = Note(0,title,content,date)
                db.insertNotes(note)
                finish()
                Toast.makeText(this, "Note Saved", Toast.LENGTH_SHORT).show()
            }
        }

    }
}