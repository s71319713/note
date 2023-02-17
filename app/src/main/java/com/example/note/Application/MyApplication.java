package com.example.note.Application;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {

    private static MyApplication instance;

    public MyApplication(){
        instance=this;
    }

    public static Context getContext(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        //銷毀清理操作
    }
}
