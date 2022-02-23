package com.task.noteapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.task.noteapp.R
import com.task.noteapp.model.NoteModel
import com.task.noteapp.view.board.NoteListFragmentDirections
import kotlinx.android.synthetic.main.note_item_container.view.*

class CustomNoteAdapter: RecyclerView.Adapter<CustomNoteAdapter.MyViewHolder>() {

    private var noteList = emptyList<NoteModel>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.note_item_container, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = noteList[position]

        holder.itemView.title_txt.text = currentItem.noteTitle
        holder.itemView.content_txt.text = currentItem.noteContent
        holder.itemView.date_txt.text = currentItem.noteCreateDate
        holder.itemView.modify_txt.visibility = if(currentItem.noteIsModified) View.VISIBLE else View.GONE

        holder.itemView.item_layout.setOnClickListener {
            val action = NoteListFragmentDirections.actionNoteListFragmentToEditNoteFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    fun setData(note: List<NoteModel>){
        this.noteList = note
        notifyDataSetChanged()
    }
}