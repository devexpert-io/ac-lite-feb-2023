package com.antonioleiva.mynotes.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antonioleiva.mynotes.Note
import com.antonioleiva.mynotes.domain.GetNoteByIdUseCase
import com.antonioleiva.mynotes.domain.SaveNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
    private val saveNoteUseCase: SaveNoteUseCase,
    private val noteId: Int
) :
    ViewModel() {

    private val _state = MutableStateFlow(Note(0, "", ""))
    val state: StateFlow<Note> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val note = getNoteByIdUseCase(noteId)
            if (note != null) {
                _state.value = note
            }
        }
    }

    fun save(title: String, description: String) {
        viewModelScope.launch {
            val note = _state.value.copy(title = title, description = description)
            saveNoteUseCase(note)
        }
    }

}