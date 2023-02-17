package com.example.note.Repository;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.note.Application.MyApplication;
import com.example.note.Dao.NoteDao;
import com.example.note.DataBase.NoteDatabase;
import com.example.note.Entity.NoteEntity;
import com.example.note.Note;
import com.example.note.callback.RepoCallback;

import org.reactivestreams.Subscriber;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class NoteRepository {
    private LiveData<List<NoteEntity>> listLiveData;
    private NoteDatabase noteDatabase;
    private NoteDao noteDao;
    private RepoCallback repoCallback;
    public final static int QUERY= 1;
    public final static int INSERT= 2;

    private Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {


            switch (msg.what){
                case QUERY:
                    LiveData<List<NoteEntity>> list = (LiveData<List<NoteEntity>>)msg.obj;
                    repoCallback.setData(list);
                    Log.d("dfdffdf", "handleMessage: ");
                case INSERT:
                    QueryAllNote();


            }
        }
    };


    public NoteRepository(RepoCallback repoCallback){
        noteDatabase= NoteDatabase.newInstance(MyApplication.getContext());
        noteDao = noteDatabase.noteDao();
        this.repoCallback = repoCallback;


    }

    public LiveData<List<NoteEntity>> getListLiveData() {
        return listLiveData;
    }

    public void insert(NoteEntity noteEntity){

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                noteDao.insert(noteEntity);
//                Message message = new Message();
//                message.what=2;
//                handler.sendMessage(message);
//                Log.d("insert", "handleMessage: "+noteEntity);
//            }
//        }).start();

    }

    public void delete(NoteEntity noteEntity){
        noteDao.delete(noteEntity);

    }

    public void update(NoteEntity noteEntity){
        noteDao.update(noteEntity);

    }

    public void deleteAll(){
        noteDao.deleteAllNotes();
    }

    public void QueryAllNote(){
        //異步

        new Thread(new Runnable() {
            @Override
            public void run() {
                listLiveData = noteDao.getAllNotes();
                Message message = new Message();
                message.what=1;
                message.obj = listLiveData;
                handler.sendMessage(message);
            }
        }).start();

//        listLiveData = noteDao.getAllNotes();
//        return listLiveData;
    }


}
