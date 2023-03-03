package com.antonioleiva.mynotes.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.antonioleiva.mynotes.NotesApplication
import com.antonioleiva.mynotes.data.NotesRepository
import com.antonioleiva.mynotes.data.NotesRoomDataSource
import com.antonioleiva.mynotes.databinding.ActivityMainBinding
import com.antonioleiva.mynotes.domain.DeleteNoteUseCase
import com.antonioleiva.mynotes.domain.GetCurrentNotesUseCase
import com.antonioleiva.mynotes.ui.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var notesAdapter: NotesAdapter

    private val vm by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            notesAdapter = NotesAdapter(
                onNoteClick = { DetailActivity.navigate(this@MainActivity, it.id) },
                onNoteDelete = { vm.onNoteDelete(it) }
            )
            recyclerView.adapter = notesAdapter
            fab.setOnClickListener {
                DetailActivity.navigate(this@MainActivity)
            }

            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    vm.state.collect {
                        notesAdapter.submitList(it)
                    }
                }
            }
        }
    }
}