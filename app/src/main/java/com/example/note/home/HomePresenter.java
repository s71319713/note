package com.example.note.home;

import com.example.note.base.BasePresenter;
import com.example.note.repository.NoteRepository;

public class HomePresenter
        extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {

    NoteRepository repository;

    public HomePresenter(){

    }


}
