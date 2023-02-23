package com.example.note.home;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.note.AddNoteFragment;
import com.example.note.EditFragment;
import com.example.note.Model.Note;
import com.example.note.R;
import com.example.note.RecycleViewAdapter;
import com.example.note.Util.ToastUtil;
import com.example.note.base.BaseActivity;
import com.example.note.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class HomeActiviy extends BaseActivity<HomePresenter> implements HomeContract.View , View.OnClickListener {

    long firstTime;
    long twiceTime;
    EditFragment editFragment;
    AddNoteFragment addNoteFragment;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        initView();
    }

    private void initView() {
        initTitleBar();
        initRecyclerView();
        initOnClickListen();
        initSwpieRefreshLayout();
    }

    private void initRecyclerView() {
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerview.setAdapter(mPresenter.getRecycleViewAdapter());
    }

    private void initSwpieRefreshLayout() {
        binding.swipebtn.setColorScheme(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light);
    }

    private void initTitleBar() {
        binding.titleBar.title.setText("記事本");
    }

    private void initOnClickListen() {
        binding.addbtn.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public HomePresenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    public void openEditView(Note note) {
        FragmentTransaction fragmentTransaction =getFragmentManager().beginTransaction();
        editFragment= EditFragment.newInstance(note);
        fragmentTransaction.replace(R.id.container,editFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void startSelectMode() {
        binding.titleBar.canclebtn.setVisibility(View.VISIBLE);
        binding.titleBar.deletebtn.setVisibility(View.VISIBLE);
    }

    @Override
    public void closeSelectMode() {
        binding.titleBar.canclebtn.setVisibility(View.GONE);
        binding.titleBar.deletebtn.setVisibility(View.GONE);
        ArrayList<Integer> positionList = mPresenter.getRecycleViewAdapter().getPositionList();
        for (Integer position:
                positionList) {
            RecycleViewAdapter.NoteViewHolder vm =   (RecycleViewAdapter.NoteViewHolder)binding.recyclerview.findViewHolderForAdapterPosition(position);
            vm.binding.getRoot().setBackgroundResource(R.color.white);
        }
        mPresenter.getRecycleViewAdapter().setIsLongClick(false);
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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addbtn:
                FragmentTransaction fragmentTransaction =getFragmentManager().beginTransaction();
                addNoteFragment = AddNoteFragment.newInstance();
                fragmentTransaction.replace(R.id.container,addNoteFragment);
                fragmentTransaction.commit();
                break;
            case R.id.swipebtn:
                mPresenter.getAll();
                binding.swipebtn.setRefreshing(false);
                break;
            case R.id.deletebtn:
                mPresenter.deldteNote(mPresenter.getRecycleViewAdapter().getDeleteList());
                closeSelectMode();
                break;
            case R.id.canclebtn:
                closeSelectMode();
                break;

        }
    }
}
