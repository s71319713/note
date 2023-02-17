package com.example.note;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.note.Entity.NoteEntity;
import com.example.note.Repository.NoteRepository;

import java.util.ArrayList;

public class NoteViewModel extends ViewModel {
    private NoteRepository noteRepository;
    private MutableLiveData<ArrayList<NoteEntity>>  liveNoteList = new MutableLiveData<>();

    public NoteViewModel(){
        liveNoteList= new MutableLiveData<ArrayList<NoteEntity>>();
//        liveNoteList = (MutableLiveData<ArrayList<NoteEntity>>) noteRepository.QueryAllNote();

    }

    public LiveData<ArrayList<NoteEntity>> getLiveNoteList(){
        return liveNoteList;
    }

    public void insert(NoteEntity entity){
       noteRepository.insert(entity);
    };
    public void update(NoteEntity entity){
        noteRepository.update(entity);
    }
    public void delete(NoteEntity entity){
        noteRepository.delete(entity);
    }
    public LiveData<ArrayList<NoteEntity>> QueryAllNote(){
        return noteRepository.QueryAllNote();
    };
    public void deleteAll(){
        noteRepository.deleteAll();
    }

}
