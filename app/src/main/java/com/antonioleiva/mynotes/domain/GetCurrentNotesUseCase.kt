package com.antonioleiva.mynotes.domain

import com.antonioleiva.mynotes.Note
import com.antonioleiva.mynotes.data.NotesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrentNotesUseCase @Inject constructor(private val notesRepository: NotesRepository) {

    operator fun invoke(): Flow<List<Note>> = notesRepository.currentNotes

}
