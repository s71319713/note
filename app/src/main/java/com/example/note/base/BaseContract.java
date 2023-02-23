package com.example.note.base;

public interface BaseContract {
    interface BaseView{

    }
    interface BasePresenter{

        void attachView (BaseView baseView);
        void detachView();
    }
}
