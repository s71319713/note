package com.example.note;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.example.note.Util.ToastUtil;
import com.example.note.callback.AddFragmentCallback;
import com.example.note.callback.EditFragmentCallback;
import com.example.note.callback.NoteCallback;
import com.example.note.databinding.ActivityMainBinding;
import com.example.note.repository.NoteRepository;
import com.example.note.table.NoteEntity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NoteCallback, EditFragmentCallback, AddFragmentCallback {

    long firstTime;
    long twiceTime;
    ActivityMainBinding binding;
    EditFragment editFragment;
    AddNoteFragment addNoteFragment;
    RecycleViewAdapter recycleViewAdapter;
    ArrayList<Note> noteArrayList=new ArrayList<>();
    NoteRepository repository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        getSupportActionBar().hide();
        repository = new NoteRepository(this);
        recycleViewAdapter = new RecycleViewAdapter(this,noteArrayList);
        initView();
        
    }

    private void initView() {
        initTitleBar();
        initRecyclerView();
        addOnClickListenLer();

    }

    private void addOnClickListenLer() {
        binding.addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction =getFragmentManager().beginTransaction();
                addNoteFragment = AddNoteFragment.newInstance();
                fragmentTransaction.replace(R.id.container,addNoteFragment);
//                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction.commit();
            }
        });
    }

    private void initRecyclerView() {
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerview.setAdapter(recycleViewAdapter);
    }

    private void initTitleBar() {
        binding.titleBar.title.setText("記事本");
    }


    @Override
    public void openEditView(Note note) {
        FragmentTransaction fragmentTransaction =getFragmentManager().beginTransaction();
        editFragment=EditFragment.newInstance(note);
        fragmentTransaction.replace(R.id.container,editFragment);
//        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getFragmentManager();
        Fragment currentFragment = fragmentManager.findFragmentById(R.id.container);
        if(currentFragment instanceof  EditFragment){
            currentFragment = (EditFragment)currentFragment;
            ((EditFragment) currentFragment).backToHome();
        }else if(currentFragment instanceof AddNoteFragment){
            currentFragment = (AddNoteFragment)currentFragment;
            ((AddNoteFragment) currentFragment).backToHome();
        }else {
            exitApp(2000);
            Log.d("exitApp", "onBackPressed: ");
        }
    }


    private void exitApp(int timeInterval) {
        if(System.currentTimeMillis()-firstTime>=timeInterval){
            ToastUtil.showToast(this,"再連按兩次退出");
            firstTime=System.currentTimeMillis();
        }else if(System.currentTimeMillis()-twiceTime>=timeInterval){
            twiceTime=System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }

    @Override
    public void deleteNote(Note note) {
//        recycleViewAdapter.deleteNote(note);
        //recycleView改成重新getAll note 並刷新
        //新增一個存資料庫的方法
        repository.delete(note.toEntity());
        refreshRecycle();

    }

    @Override
    public void refresh() {
        recycleViewAdapter.refresh();
    }

    public void updateNote(Note note){
        repository.update(note.toEntity());
        refreshRecycle();
    }

    @Override
    public void addNote(Note note) {
//        recycleViewAdapter.addNote(note);
        //recycleView改成重新getAll note 並刷新
        //新增一個存資料庫的方法
        repository.insert(note.toEntity());
        refreshRecycle();

    }

    public List<Note> getAll(){
        List<Note> noteArrayList = new ArrayList<Note>();
        List<NoteEntity> noteEntities = repository.getAll();

        for (NoteEntity entity:
             noteEntities) {
            noteArrayList.add(entity.toNote());
        }

       return noteArrayList;
    }


    public void refreshRecycle(){
        recycleViewAdapter.refreshFromDB();
    }
}