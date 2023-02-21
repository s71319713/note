package com.example.note.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.note.table.NoteEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface NoteDao {

    @Insert
    public Completable insertNote(NoteEntity entity);

    @Delete
    public Completable deleteNote(NoteEntity entity);

    @Update
    public Completable updateNote(NoteEntity entity);

    @Query("select * from note_table where id=:id")
    public Single<NoteEntity> queryById(int id);

    @Query("select * from note_table order by date desc")
    public Single<List<NoteEntity>> queryAll();

    @Query("SELECT * FROM note_table WHERE content LIKE '%' || :search || '%'")
    public Single<List<NoteEntity>> queryByContent(String search);

}
