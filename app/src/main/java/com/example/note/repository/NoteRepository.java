package com.example.note.repository;

import android.content.Context;
import android.content.Entity;

import com.example.note.Note;
import com.example.note.database.NoteDatabase;
import com.example.note.table.NoteEntity;

import java.util.List;

public class NoteRepository {
    private NoteDatabase noteDatabase;
    public NoteRepository(Context context){
        noteDatabase = NoteDatabase.getDBInstance(context);
    }

    public void insert(NoteEntity note){
        noteDatabase.getNoteDao().insertNote(note);
    }



    public void delete(NoteEntity note){

    }

    public void update(NoteEntity note){

    }

    public List<NoteEntity> getAll(){
        return null;
    };

    public List<Entity> search(String key){

        return  null;
    };




}
