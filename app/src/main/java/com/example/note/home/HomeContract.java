package com.example.note.home;

import androidx.recyclerview.widget.RecyclerView;

import com.example.note.AddNoteFragment;
import com.example.note.EditFragment;
import com.example.note.Model.Note;
import com.example.note.RecycleViewAdapter;
import com.example.note.base.BaseContract;

import java.util.ArrayList;

public interface HomeContract {
    interface View extends BaseContract.BaseView{

        void openEditView(Note note);

        void startSelectMode();

        void closeSelectMode();

        AddNoteFragment getAddNoteFragment();

        EditFragment getEditFagment();
    }

    interface Presenter extends BaseContract.BasePresenter{

        RecycleViewAdapter getRecycleViewAdapter();
        void getAll();
        void setAddFragmentCallBack();
        void deldteNote(ArrayList<Note> deleteList);
        void setEditFragmentCallBack();
    }
}
