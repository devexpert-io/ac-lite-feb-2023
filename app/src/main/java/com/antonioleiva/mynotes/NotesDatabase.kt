package com.antonioleiva.mynotes

/* Create a notes database using Room with all CRUD operations. */

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


@Database(entities = [Note::class], version = 1)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun notesDao(): NoteDao
}

@Dao
interface NoteDao {

    @Query("SELECT * FROM Note")
    suspend fun getAll(): List<Note>

    @Query("SELECT * FROM Note WHERE id = :id")
    suspend fun getById(id: Int): Note?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Update
    suspend fun update(note: Note)

    @Delete
    suspend fun delete(note: Note)
}