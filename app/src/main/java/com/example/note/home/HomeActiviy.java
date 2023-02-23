package com.example.note.home;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.note.base.BaseActivity;

public class HomeActiviy extends BaseActivity<HomePresenter> implements HomeContract.View {
    @Override
    public HomePresenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
