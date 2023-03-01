package com.antonioleiva.mynotes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.antonioleiva.mynotes.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var notesAdapter: NotesAdapter
    lateinit var database: NotesDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = (application as NotesApplication).notesDatabase
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            notesAdapter = NotesAdapter(
                onNoteClick = { DetailActivity.navigate(this@MainActivity, it.id) },
                onNoteDelete = {
                    lifecycleScope.launch {
                        database.notesDao().delete(it)
                        notesAdapter.submitList(database.notesDao().getAll())
                    }
                }
            )
            recyclerView.adapter = notesAdapter
            fab.setOnClickListener {
                DetailActivity.navigate(this@MainActivity)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            notesAdapter.submitList(database.notesDao().getAll())
        }
    }
}