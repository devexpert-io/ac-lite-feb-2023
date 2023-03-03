package com.antonioleiva.mynotes

import android.app.Application
import androidx.room.Room
import com.antonioleiva.mynotes.data.NotesDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NotesApplication : Application() {

    lateinit var notesDatabase: NotesDatabase
        private set

    override fun onCreate() {
        super.onCreate()

    }

}