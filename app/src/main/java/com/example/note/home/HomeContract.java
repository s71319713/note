package com.example.note.home;

import com.example.note.Model.Note;
import com.example.note.base.BaseContract;

public interface HomeContract {
    interface View extends BaseContract.BaseView{

        void openEditView(Note note);

        void startSelectMode();

        void closeSelectMode();
    }

    interface Presenter extends BaseContract.BasePresenter{

    }
}
