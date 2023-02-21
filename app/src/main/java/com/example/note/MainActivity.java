package com.example.note;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.example.note.Model.Note;
import com.example.note.Util.ToastUtil;
import com.example.note.callback.AddFragmentCallback;
import com.example.note.callback.EditFragmentCallback;
import com.example.note.callback.ReycleviewCallback;
import com.example.note.callback.NoteRepositoryCallback;
import com.example.note.databinding.ActivityMainBinding;
import com.example.note.repository.NoteRepository;
import com.example.note.table.NoteEntity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ReycleviewCallback, EditFragmentCallback, AddFragmentCallback, NoteRepositoryCallback {

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
        recycleViewAdapter = new RecycleViewAdapter(this);
        initView();
        
    }

    private void initView() {
        initTitleBar();
        initRecyclerView();
        addOnClickListenLer();
        initSwpieRefreshLayout();

    }

    private void initSwpieRefreshLayout() {
        binding.swipebtn.setColorScheme(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light);
        binding.swipebtn.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAll();
                binding.swipebtn.setRefreshing(false);
            }
        });
    }

    private void addOnClickListenLer() {
        binding.addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction =getFragmentManager().beginTransaction();
                addNoteFragment = AddNoteFragment.newInstance();
                fragmentTransaction.replace(R.id.container,addNoteFragment);
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
        fragmentTransaction.commit();
    }

    @Override
    public void getAll() {
        repository.getAll();
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
        repository.delete(note.toEntity());

    }



    public void updateNote(Note note){
        repository.update(note.toEntity());


    }

    @Override
    public void addNote(Note note) {
        repository.insert(note.toEntity());
    }


    @Override
    public void setData(List<NoteEntity> entityList) {
        List<Note> noteArrayList = new ArrayList<Note>();

        for (NoteEntity entity:
                entityList) {
            noteArrayList.add(entity.toNote());
        }
        recycleViewAdapter.setData(noteArrayList);
    }
}