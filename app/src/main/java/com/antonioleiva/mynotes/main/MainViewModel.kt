package com.antonioleiva.mynotes.main

import androidx.lifecycle.*
import com.antonioleiva.mynotes.Note
import com.antonioleiva.mynotes.NotesDatabase
import kotlinx.coroutines.launch

class MainViewModel(private val db: NotesDatabase) : ViewModel() {

    val state = db.notesDao().getAll()

    fun onNoteDelete(note: Note) {
        viewModelScope.launch {
            db.notesDao().delete(note)
        }
    }
}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val db: NotesDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(db) as T
    }
}