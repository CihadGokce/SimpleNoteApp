package com.task.noteapp.view.insert

import android.app.Activity
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.task.noteapp.R
import com.task.noteapp.model.NoteModel
import com.task.noteapp.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_insert_note.*
import kotlinx.android.synthetic.main.fragment_insert_note.view.*
import java.sql.Array
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

class InsertNoteFragment : Fragment() {

    private lateinit var mNoteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_insert_note, container, false)

        mNoteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        view.insert_btn.setOnClickListener {
            insertDataToDatabase()
        }



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hide()
    }



    private fun insertDataToDatabase(){
        val title = insertTitle_et.text.toString()
        val topic = insertContext_et.text.toString()

        val currentDate = Calendar.getInstance(Locale.getDefault())
        val year = currentDate.get(Calendar.YEAR)
        var month = currentDate.get(Calendar.MONTH)
        month ++;
        var monthStr = month.toString()
        if(month<10) monthStr = "0" + monthStr // dd/mm/yyyy formatına uygun olması için
        val day = currentDate.get(Calendar.DAY_OF_MONTH)
        val dateTime = "${day}/${monthStr}/${year}"

        var date = dateTime

        if (inputCheck(title, topic)){
            val note = NoteModel(0, title, topic, date,false)
            mNoteViewModel.insertNote(note)
            Toast.makeText(requireContext(), R.string.insert_note_success_msg, Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_insertNoteFragment_to_noteListFragment)
        }else{
            Toast.makeText(requireContext(), R.string.insert_note_err_msg, Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(title: String, topic: String): Boolean{
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(topic))
    }

    private fun hide(){
        scrollView.setOnTouchListener { view, motionEvent ->
            closeKeyboard()

        }
    }

    private fun closeKeyboard(): Boolean{
        val view = requireActivity().currentFocus
        if (view!=null){
            val imm = view.context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
        return true
    }





}