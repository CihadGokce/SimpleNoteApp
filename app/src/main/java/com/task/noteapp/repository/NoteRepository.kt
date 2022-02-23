package com.task.noteapp.repository

import androidx.lifecycle.LiveData
import com.task.noteapp.db.NoteDao
import com.task.noteapp.model.NoteModel

class NoteRepository(private val noteDao: NoteDao) {

    fun readAllData() : LiveData<List<NoteModel>> {
        return  noteDao.readAllData()
    }
    suspend fun getData(id: Int) : NoteModel {
        return noteDao.getData(id)
    }

    suspend fun insertNote(note: NoteModel){
        noteDao.insertNote(note)
    }

    suspend fun editNote(note: NoteModel){
        noteDao.editNote(note)
    }

    suspend fun deleteNote(note: NoteModel){
        noteDao.deleteNote(note)
    }

    suspend fun deleteAllNotes(){
        noteDao.deleteAllNotes()
    }
}