package com.example.note;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.note.Application.MyApplication;
import com.example.note.Entity.NoteEntity;
import com.example.note.Repository.NoteRepository;
import com.example.note.callback.RepoCallback;

import java.util.List;

public class NoteViewModel extends ViewModel implements RepoCallback {
    private NoteRepository noteRepository;
    private MutableLiveData<List<NoteEntity>>  liveNoteList = new MutableLiveData<>();

    public NoteViewModel(){
        liveNoteList= new MutableLiveData<List<NoteEntity>>();
        Log.d("ccccc", "NoteViewModel: "+ MyApplication.getContext());
        noteRepository = new NoteRepository(this);
        loadDatas();


    }

    private void loadDatas() {
        noteRepository.QueryAllNote();
//        liveNoteList.setValue( .getValue());

            }

            @Override
            public void setData(LiveData<List<NoteEntity>> listLiveData){
                liveNoteList.setValue( listLiveData.getValue());
            }




    public LiveData<List<NoteEntity>> getLiveNoteList(){
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
    public void QueryAllNote(){
//        return noteRepository.QueryAllNote();
    };
    public void deleteAll(){
        noteRepository.deleteAll();
    }

}
