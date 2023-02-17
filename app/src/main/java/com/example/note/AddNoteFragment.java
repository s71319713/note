package com.example.note;

import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.example.note.Util.TimeUtils;
import com.example.note.callback.AddFragmentCallback;
import com.example.note.databinding.ViewAddBinding;

public class AddNoteFragment extends android.app.Fragment {

    ViewAddBinding binding;
    AddFragmentCallback addFragmentCallback;
    Context context;
    



    public AddNoteFragment() {

    }

    private void initTitleBar() {
        binding.titleBar.title.setText("新增記事");
        binding.titleBar.backBtn.setVisibility(View.VISIBLE);
    }

    public static AddNoteFragment newInstance() {
        AddNoteFragment fragment = new AddNoteFragment();
        return fragment;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        if(context instanceof AddFragmentCallback){
            addFragmentCallback = (AddFragmentCallback)context;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        ViewGroup parent = (ViewGroup) container.getParent();
//        if (parent != null) {
//            parent.removeView(container);
//        }
//        binding = ViewEditBinding.bind(LayoutInflater.from(context).inflate(R.layout.view_edit,container));
//        initView();
//        return binding.getRoot();
        binding = ViewAddBinding.bind(LayoutInflater.from(context).inflate(R.layout.view_add,container,false));

//        if (container != null) {
//            container.removeView(binding.getRoot());
//        }
        initView();
        return binding.getRoot();
    }

    private void initView() {
        initTitleBar();
        initOnClickListener();
    }

    private void initOnClickListener() {
        binding.titleBar.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                backToHome();
            }
        });
    }

    private void addNote(){
        //新增
        Note note = new Note();
        if((binding.edittext.getText().toString()).equals("")){
            return;}
        note.content = binding.edittext.getText().toString();
        note.lastUpdate = TimeUtils.getCurrentTime();
        addFragmentCallback.addNote(note);


    }


    public void backToHome(){
        hideKeyboard();
        getActivity().getFragmentManager().beginTransaction().remove(this).commit();

    }

    private void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }


    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        addNote();
//        addNote();
    }
}