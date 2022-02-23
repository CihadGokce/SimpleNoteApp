package com.task.noteapp.view.board

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.task.noteapp.R
import com.task.noteapp.adapter.CustomNoteAdapter
import com.task.noteapp.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_note_list.view.*

class NoteListFragment : Fragment() {

    private lateinit var mNoteViewModel: NoteViewModel
    private var gridLayoutManager: GridLayoutManager? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_note_list, container, false)

        val adapter = CustomNoteAdapter()
        val recyclerView = view.recyclerview
        recyclerView.adapter = adapter

        gridLayoutManager = GridLayoutManager(requireContext(), 2, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = gridLayoutManager
        recyclerView.setHasFixedSize(true)


        mNoteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        mNoteViewModel.readAllData.observe(viewLifecycleOwner, Observer { note ->
            adapter.setData(note)
        })


        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_noteListFragment_to_insertNoteFragment)
        }

        setHasOptionsMenu(true)

        return view
    }

}