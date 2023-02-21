package com.example.note.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.note.Note;
import com.example.note.table.NoteEntity;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert
    public void insertNote(NoteEntity entity);

    @Delete
    public void deleteNote(NoteEntity entity);

    @Update
    public void UpdateNote(NoteEntity entity);

    @Query("select * from note_table where id=:id")
    public void queryById(int id);

    @Query("select * from note_table")
    public List<NoteEntity> queryAll();

    @Query("SELECT * FROM note_table WHERE content LIKE '%' || :search || '%'")
    public List<NoteEntity> queryByContent(String search);

}
