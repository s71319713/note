package com.example.note.Dao;

import android.database.Observable;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.note.Entity.NoteEntity;
import com.example.note.Note;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface NoteDao {

    @Insert
    void insert(NoteEntity noteEntity);

    @Update
    void update(NoteEntity noteEntity);

    @Delete
    void delete(NoteEntity noteEntity);

    @Query("DELETE FROM note_table")
    void deleteAllNotes();

    @Query("SELECT * FROM note_table")
    LiveData<List<NoteEntity>> getAllNotes();

//    @Query("SELECT * FROM note_table ")
//    MutableLiveData<ArrayList<Note>> getAllNotes();


}
