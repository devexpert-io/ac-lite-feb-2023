package com.antonioleiva.mynotes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.antonioleiva.mynotes.databinding.NoteItemBinding

class NotesAdapter(
    private val onNoteClick: (Note) -> Unit,
    private val onNoteDelete: (Note) -> Unit
) : ListAdapter<Note, NoteViewHolder>(NoteDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position), onNoteClick, onNoteDelete)
    }
}

class NoteDiffCallback : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }
}

class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = NoteItemBinding.bind(view)
    fun bind(note: Note, onNoteClick: (Note) -> Unit, onNoteDelete: (Note) -> Unit) {
        binding.title.text = note.title
        binding.root.setOnClickListener { onNoteClick(note) }
        binding.delete.setOnClickListener { onNoteDelete(note) }
    }
}
