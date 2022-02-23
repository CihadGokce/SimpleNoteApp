package com.task.noteapp.repository

import android.app.Application
import com.google.common.truth.Truth
import com.task.noteapp.FakeNoteModel
import com.task.noteapp.db.NoteDao
import com.task.noteapp.db.NoteDatabase
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class NoteRepositoryTest {

    @RelaxedMockK
    private lateinit var repository: NoteRepository

    private lateinit var noteDao: NoteDao

    val context = mockk<Application>(relaxed = true)


    @Before
    fun before() {
        noteDao = NoteDatabase.getDatabase(context).noteDao()
        repository = NoteRepository(noteDao)
    }

    @After
    fun after() {

    }

    fun testGetReadAllData() {}

    @Test
    fun testInsertNote() = runBlocking {

        val model = FakeNoteModel.getNoteModel()
        repository.insertNote(model)
        val response = repository.getData(1)
        Truth.assertThat(response).isEqualTo(model)

    }

    fun testEditNote() {}

    fun testDeleteNote() {}

    fun testDeleteAllNotes() {}
}