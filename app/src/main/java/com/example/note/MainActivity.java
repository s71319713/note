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

import com.example.note.Utill.ToastUtil;
import com.example.note.callback.AddFragmentCallback;
import com.example.note.callback.EditFragmentCallback;
import com.example.note.callback.NoteCallback;
import com.example.note.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NoteCallback, EditFragmentCallback, AddFragmentCallback {

    long firstTime;
    ActivityMainBinding binding;
    EditFragment editFragment;
    AddNoteFragment addNoteFragment;
    RecycleViewAdapter recycleViewAdapter;
    ArrayList<Note> noteArrayList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        getSupportActionBar().hide();
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
            ToastUtil.showToast(this,"再按一次退出");
            firstTime=System.currentTimeMillis();
        }else {
            finish();
            System.exit(0);
        }
    }

    @Override
    public void deleteNote(Note note) {
        recycleViewAdapter.deleteNote(note);
    }

    @Override
    public void refresh() {
        recycleViewAdapter.refresh();
    }

    @Override
    public void addNote(Note note) {
        recycleViewAdapter.addNote(note);
    }
}