package com.example.note.home;

import android.util.Log;

import com.example.note.Model.Note;
import com.example.note.RecycleViewAdapter;
import com.example.note.application.App;
import com.example.note.base.BasePresenter;
import com.example.note.callback.AddFragmentCallback;
import com.example.note.callback.EditFragmentCallback;
import com.example.note.callback.NoteRepositoryCallback;
import com.example.note.callback.ReycleviewCallback;
import com.example.note.repository.NoteRepository;
import com.example.note.table.NoteEntity;

import java.util.ArrayList;
import java.util.List;

public class HomePresenter
        extends BasePresenter<HomeContract.View> implements HomeContract.Presenter,
        ReycleviewCallback, EditFragmentCallback, AddFragmentCallback, NoteRepositoryCallback {

    NoteRepository repository;
    RecycleViewAdapter recycleViewAdapter;

    public HomePresenter(){
        repository = new NoteRepository(this);
        recycleViewAdapter = new RecycleViewAdapter(this);

    }

    @Override
    public RecycleViewAdapter getRecycleViewAdapter() {
        return recycleViewAdapter;
    }

    @Override
    public void openEditView(Note note) {
        view.openEditView(note);
    }

    @Override
    public void getAll(){
        repository.getAll();
    }

    @Override
    public void startSelectMode() {
        view.startSelectMode();

    }


    @Override
    public void deleteNote(Note note) {
        repository.delete(note.toEntity());
    }



    @Override
    public void deldteNote(ArrayList<Note> deleteList) {
        List<NoteEntity> entityList=new ArrayList<>();
        for (Note note:deleteList){
            entityList.add(note.toEntity());
        }
        repository.delete(entityList);

    }

    public void updateNote(Note note){
        repository.update(note.toEntity());


    }

    public void addNote(Note note) {
        repository.insert(note.toEntity());
    }

    public void setData(List<NoteEntity> entityList) {
        List<Note> noteArrayList = new ArrayList<Note>();

        for (NoteEntity entity:
                entityList) {
            noteArrayList.add(entity.toNote());
        }
        recycleViewAdapter.setData(noteArrayList);
    }
@Override
    public void setAddFragmentCallBack() {
        view.getAddNoteFragment().setAddFragmentCallback(this);
    }



    @Override
    public void setEditFragmentCallBack() {
        view.getEditFagment().setEditFragmentCallback(this);
    }
}
