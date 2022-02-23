package com.task.noteapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.task.noteapp.model.NoteModel

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE) // Eski verileri değiştirip işleme devam ediyor
    suspend fun insertNote(noteModel: NoteModel)

    @Update
    suspend fun editNote(noteModel: NoteModel)

    @Delete
    suspend fun deleteNote(noteModel: NoteModel)

    @Query("DELETE FROM GetirNotesTable")
    suspend fun deleteAllNotes()


    @Query("SELECT * FROM GetirNotesTable ORDER BY id ASC")
    fun readAllData(): LiveData<List<NoteModel>>

    @Query("SELECT * FROM GetirNotesTable WHERE id =:id")
    suspend fun getData(id: Int): NoteModel


}
