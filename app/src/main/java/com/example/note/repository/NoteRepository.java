package com.example.note.repository;

import android.content.Context;
import android.content.Entity;
import android.util.Log;

import com.example.note.Note;
import com.example.note.callback.EditFragmentCallback;
import com.example.note.callback.NoteRepositoryCallback;
import com.example.note.database.NoteDatabase;
import com.example.note.table.NoteEntity;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NoteRepository{
    private NoteDatabase noteDatabase;
    private NoteRepositoryCallback noteRepositoryCallback;
    public NoteRepository(Context context){
        noteDatabase = NoteDatabase.getDBInstance(context);
        if(context instanceof NoteRepositoryCallback){
            noteRepositoryCallback = (NoteRepositoryCallback)context;
        }
    }

    public void insert(NoteEntity note){
        noteDatabase.getNoteDao().insertNote(note)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d("DBBBBB", "onComplete: 新增成功");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("DBBBBB", "onComplete: 新增失敗"+e.toString());
                    }
                });

    }



    public void delete(NoteEntity note){
        noteDatabase.getNoteDao().deleteNote(note)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d("DBBBBB", "onComplete: 刪除成功");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("DBBBBB", "onComplete: 刪除失敗"+e.toString());
                    }
                });

    }

    public void update(NoteEntity note){

        noteDatabase.getNoteDao().updateNote(note)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d("DBBBBB", "onComplete: 更新成功");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("DBBBBB", "onComplete: 更新失敗"+e.toString());
                    }
                });
    }

    public void getAll(){


        noteDatabase.getNoteDao().queryAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<NoteEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<NoteEntity> noteEntities) {
                        noteRepositoryCallback.setData(noteEntities);
                        Log.d("DBBBBB", "onSuccess: 取得所有成功");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("DBBBBB", "onComplete: 取得所有失敗"+e.toString());
                    }
                });

    };

    public void search(String key){
        noteDatabase.getNoteDao().queryByContent(key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<NoteEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<NoteEntity> noteEntities) {
                        noteRepositoryCallback.setData(noteEntities);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });

    };






}
