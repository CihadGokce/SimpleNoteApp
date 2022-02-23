package com.task.noteapp.view.edit

import android.app.AlertDialog
import android.os.Bundle
import android.text.Html
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.task.noteapp.R
import com.task.noteapp.model.NoteModel
import com.task.noteapp.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_edit_note.*
import kotlinx.android.synthetic.main.fragment_edit_note.view.*

class EditNoteFragment : Fragment(){

    private val args by navArgs<EditNoteFragmentArgs>()
    private lateinit var  mNoteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit_note, container, false)

        mNoteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        view.editTitle_et.setText(args.currentNote.noteTitle)
        view.editContent_et.setText(args.currentNote.noteContent)
        view.editDate_txt.setText(args.currentNote.noteCreateDate)
        view.text_edit_modify.visibility = if(args.currentNote.noteIsModified) View.VISIBLE else View.GONE

        view.edit_btn.setOnClickListener {
            editItem()
        }

        setHasOptionsMenu(true)

        return view
    }

    private fun editItem(){
        val title = editTitle_et.text.toString()
        val topic = editContent_et.text.toString()

        if (inputCheck(title, topic)){
            val editedNote = NoteModel(args.currentNote.id, title, topic, args.currentNote.noteCreateDate,true)
            mNoteViewModel.editNote(editedNote)
            Toast.makeText(requireContext(), R.string.update_note_success_msg, Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_editNoteFragment_to_noteListFragment)
        }else{
            Toast.makeText(requireContext(), R.string.insert_note_err_msg, Toast.LENGTH_LONG).show()
        }

    }

    private fun inputCheck(title: String, topic: String): Boolean{
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(topic))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.config_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete_menu){
            deleteNote()
        }else if(item.itemId == R.id.edit_menu){
            editTitle_et.isEnabled = true
            editContent_et.isEnabled = true
            edit_btn.visibility = View.VISIBLE
        }

        return super.onOptionsItemSelected(item)
    }

    private fun deleteNote(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton(R.string.yes){ _, _ ->
            mNoteViewModel.deleteNote(args.currentNote)
            Toast.makeText(requireContext(), R.string.delete_note_success_msg, Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_editNoteFragment_to_noteListFragment)
        }
        builder.setNegativeButton(R.string.no){ _, _ ->

        }
        builder.setTitle(R.string.warning)
        builder.setMessage(Html.fromHtml("Başlığı "+"<b>${args.currentNote.noteTitle}</b>" +" Olan Notunuzu Silmek İstediğinize Emin Misiniz ?"))
        builder.create().show()
    }
}