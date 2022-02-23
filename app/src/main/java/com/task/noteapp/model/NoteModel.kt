package com.task.noteapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "GetirNotesTable")
data class NoteModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val noteTitle: String,
    val noteContent: String,
    val noteCreateDate: String,
    val noteIsModified: Boolean
):Parcelable