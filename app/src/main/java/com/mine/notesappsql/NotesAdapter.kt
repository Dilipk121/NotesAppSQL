package com.mine.notesappsql

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import java.text.DateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar

class NotesAdapter(private var notes:List<Note>,context: Context) :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

        private val db:NoteDataBaseHelper = NoteDataBaseHelper(context)

    class NotesViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

        val title_tv:TextView = itemView.findViewById(R.id.add_title)
        val content_tv:TextView = itemView.findViewById(R.id.add_content)
        val date_time:TextView = itemView.findViewById(R.id.date_time)
        val updateBtn:ImageView = itemView.findViewById(R.id.update_btn)
        val deleteBtn:ImageView = itemView.findViewById(R.id.delete_btn)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notes_item,parent,false)
        return NotesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {

        val note = notes[position]
        holder.title_tv.text = note.title
        holder.content_tv.text = note.description
        holder.date_time.text = note.date


        holder.updateBtn.setOnClickListener{
            val intent = Intent(holder.itemView.context,UpdateNoteActivity::class.java).apply {
                putExtra("note_id",note.id)
            }
            //here we need holde + itemview.context to call start activity
            holder.itemView.context.startActivity(intent)
        }

        holder.deleteBtn.setOnClickListener{
            db.deleteNote(note.id)
            refreshNotes(db.getAllNotes())
            Toast.makeText(holder.itemView.context, "Note Deleted !!", Toast.LENGTH_SHORT).show()
        }

    }

    fun refreshNotes(newNotes: List<Note>){
        notes = newNotes
        notifyDataSetChanged()
    }

}