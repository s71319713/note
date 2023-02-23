package com.example.note.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity<T extends BaseContract.BasePresenter>
        extends AppCompatActivity implements BaseContract.BaseView {

    //還沒初始化需要交給實現類的實現createPresenter()傳入
    protected T mPresenter;

    public abstract T createPresenter();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(mPresenter!=null){
            mPresenter = createPresenter();
        }
        attachPresenter(mPresenter);
    }

    public void attachPresenter(T mPresenter){
        this.mPresenter = mPresenter;
        //把接口傳入
        mPresenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //銷毀會造成內存洩漏的任何東西
        mPresenter.detachView();
    }
}
