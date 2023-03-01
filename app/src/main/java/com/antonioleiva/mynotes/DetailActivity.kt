package com.antonioleiva.mynotes

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.antonioleiva.mynotes.databinding.ActivityDetailBinding
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_NOTE_ID = "note_id"

        fun navigate(activity: AppCompatActivity, noteId: Int = -1) {
            val intent = Intent(activity, DetailActivity::class.java).apply {
                putExtra(EXTRA_NOTE_ID, noteId)
            }
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityDetailBinding.inflate(layoutInflater).apply {
            setContentView(root)
            lifecycleScope.launch {
                val database = (application as NotesApplication).notesDatabase
                val note = database.notesDao().getById(intent.getIntExtra(EXTRA_NOTE_ID, -1))
                if (note != null) {
                    editTextTitle.setText(note.title)
                    editTextDescription.setText(note.description)
                }

                buttonSave.setOnClickListener {
                    val title = editTextTitle.text.toString()
                    val description = editTextDescription.text.toString()
                    lifecycleScope.launch {
                        if (note != null) {
                            database.notesDao()
                                .update(note.copy(title = title, description = description))
                        } else {
                            database.notesDao()
                                .insert(Note(0, title = title, description = description))
                        }
                        finish()
                    }
                }

            }
        }
    }
}