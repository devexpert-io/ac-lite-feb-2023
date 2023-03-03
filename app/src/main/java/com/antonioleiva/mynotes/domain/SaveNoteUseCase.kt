package com.antonioleiva.mynotes.domain

import com.antonioleiva.mynotes.Note
import com.antonioleiva.mynotes.data.NotesRepository
import javax.inject.Inject

class SaveNoteUseCase @Inject constructor(private val notesRepository: NotesRepository) {

    suspend operator fun invoke(note: Note) {
        notesRepository.save(note)
    }

}
