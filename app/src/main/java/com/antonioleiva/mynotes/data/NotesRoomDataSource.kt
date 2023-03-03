package com.antonioleiva.mynotes.data

import com.antonioleiva.mynotes.Note
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface NotesLocalDataSource {
    val currentNotes: Flow<List<Note>>

    suspend fun delete(note: Note)

    suspend fun getById(noteId: Int): Note?

    suspend fun save(note: Note)
}

class NotesRoomDataSource @Inject constructor(private val noteDao: NoteDao) : NotesLocalDataSource {
    override val currentNotes: Flow<List<Note>> = noteDao.getAll()

    override suspend fun delete(note: Note) {
        noteDao.delete(note)
    }

    override suspend fun getById(noteId: Int): Note? = noteDao.getById(noteId)

    override suspend fun save(note: Note) {
        noteDao.insert(note)
    }
}