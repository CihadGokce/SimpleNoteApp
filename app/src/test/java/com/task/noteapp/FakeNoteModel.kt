package com.task.noteapp

import com.task.noteapp.model.NoteModel

object FakeNoteModel {

    fun getNoteModel(): NoteModel {
        return (NoteModel(1,
            "fake title",
            "test",
            "12/12/2002",
            false))
    }

}