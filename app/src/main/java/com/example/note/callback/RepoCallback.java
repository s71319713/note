package com.example.note.callback;

import androidx.lifecycle.LiveData;

import com.example.note.Entity.NoteEntity;

import java.util.List;

public interface RepoCallback {
    void setData(LiveData<List<NoteEntity>> listLiveData);
}
