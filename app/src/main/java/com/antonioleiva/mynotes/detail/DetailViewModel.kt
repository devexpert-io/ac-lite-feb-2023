package com.antonioleiva.mynotes.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.antonioleiva.mynotes.Note
import com.antonioleiva.mynotes.NotesDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val database: NotesDatabase, private val noteId: Int) :
    ViewModel() {

    private val _state = MutableStateFlow(Note(0, "", ""))
    val state: StateFlow<Note> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val note = database.notesDao().getById(noteId)
            if (note != null) {
                _state.value = note
            }
        }
    }

    fun save(title: String, description: String) {
        viewModelScope.launch {
            val note = _state.value.copy(title = title, description = description)
            database.notesDao().insert(note)
        }
    }

}

@Suppress("UNCHECKED_CAST")
class DetailViewModelFactory(private val database: NotesDatabase, private val noteId: Int) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailViewModel(database, noteId) as T
    }
}