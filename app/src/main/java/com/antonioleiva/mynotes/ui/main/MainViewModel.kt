package com.antonioleiva.mynotes.ui.main

import androidx.lifecycle.*
import com.antonioleiva.mynotes.Note
import com.antonioleiva.mynotes.domain.DeleteNoteUseCase
import com.antonioleiva.mynotes.domain.GetCurrentNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    getCurrentNotesUseCase: GetCurrentNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
) : ViewModel() {

    val state = getCurrentNotesUseCase()

    fun onNoteDelete(note: Note) {
        viewModelScope.launch {
            deleteNoteUseCase(note)
        }
    }
}