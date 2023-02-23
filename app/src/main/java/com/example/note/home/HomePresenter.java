package com.example.note.home;

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
        repository = new NoteRepository(App.getContext());
        recycleViewAdapter = new RecycleViewAdapter(App.getContext());
    }

    public RecycleViewAdapter getRecycleViewAdapter() {
        return recycleViewAdapter;
    }

    @Override
    public void openEditView(Note note) {
        view.openEditView(note);
    }

    public void getAll(){
        repository.getAll();
    }

    @Override
    public void startSelectMode() {
        view.startSelectMode();

    }

    public void closeSelectMode(){
        view.closeSelectMode();
    }

    public void deleteNote(List<Note> noteList) {
        List<NoteEntity> entityList=new ArrayList<>();
        for (Note note:noteList){
            entityList.add(note.toEntity());
        }
        repository.delete(entityList);

    }



    @Override
    public void deleteNote(Note note) {
        repository.delete(note.toEntity());
    }

    public void deldteNote(List<Note> noteList){
        List<NoteEntity> entityList=new ArrayList<>();
        for (Note note:noteList){
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
}
