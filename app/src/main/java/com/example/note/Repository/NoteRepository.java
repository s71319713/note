package com.example.note.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.note.Application.MyApplication;
import com.example.note.Dao.NoteDao;
import com.example.note.DataBase.NoteDatabase;
import com.example.note.Entity.NoteEntity;
import com.example.note.Note;

import java.util.ArrayList;

public class NoteRepository {
    private MutableLiveData<ArrayList<NoteEntity>> listLiveData;
    private NoteDatabase noteDatabase;
    private NoteDao noteDao;

    public NoteRepository(){
        noteDatabase= NoteDatabase.newInstance(MyApplication.getContext());
        noteDao = noteDatabase.noteDao();


    }

    public MutableLiveData<ArrayList<NoteEntity>> getListLiveData() {
        return listLiveData;
    }

    public void insert(NoteEntity noteEntity){

        noteDao.insert(noteEntity);
    }

    public void delete(NoteEntity noteEntity){
        noteDao.delete(noteEntity);

    }

    public void update(NoteEntity noteEntity){
        noteDao.update(noteEntity);

    }

    public void deleteAll(){
        noteDao.deleteAllNotes();
    }

    public MutableLiveData<ArrayList<NoteEntity>> QueryAllNote(){
        //異步
//        listLiveData = noteDao.getAllNotes();
        return listLiveData;
    }


}
