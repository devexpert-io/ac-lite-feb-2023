package com.antonioleiva.mynotes.domain

import com.antonioleiva.mynotes.Note
import com.antonioleiva.mynotes.data.NotesRepository
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(private val notesRepository: NotesRepository) {

    suspend operator fun invoke(note: Note) {
        notesRepository.delete(note)
    }

}
