package com.example.note;

import static com.example.note.Utill.Constant.EDIT;

import android.content.Context;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.note.callback.EditFragmentCallback;
import com.example.note.databinding.ViewEditBinding;


public class EditFragment extends android.app.Fragment  {
    ViewEditBinding binding;
    EditFragmentCallback editFragmentCallback;
    Context context;
    Note note;

    public EditFragment() {

    }

    private void initTitleBar() {
        binding.titleBar.title.setText("編輯記事");
        binding.titleBar.backBtn.setVisibility(View.VISIBLE);
    }

    public static EditFragment newInstance(Note note) {
        EditFragment fragment = new EditFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EDIT, note);
        fragment.setArguments(bundle);
        return fragment;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //拿callback&context
        this.context = context;
        if(context instanceof EditFragmentCallback){
            editFragmentCallback = (EditFragmentCallback)context;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            Note note = (Note) bundle.getSerializable(EDIT);
            this.note = note;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


//        binding = ViewEditBinding.bind(LayoutInflater.from(context).inflate(R.layout.view_edit,container));
//        initView();
//        binding.edittext.setText(note.content);
//        binding.lastupdatetext.setText(note.lastUpdate);
//        return binding.getRoot();

        binding = ViewEditBinding.bind(LayoutInflater.from(context).inflate(R.layout.view_edit,container,false));

//        if (container != null) {
//            container.removeView(binding.getRoot());
//        }
        //變透明的罪魁禍首== 可是不移會發瘋啊
        initView();
        return binding.getRoot();
    }

    private void initView() {
        binding.edittext.setText(note.content);
        binding.lastupdatetext.setText(note.lastUpdate);
        initTitleBar();
        initOnClickListener();
    }

    private void initOnClickListener() {
        binding.deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteNote();
                backToHome();
            }
        });

        binding.titleBar.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                backToHome();
            }
        });
    }

    private void saveNote(){
        //儲存
        note.content = binding.edittext.getText().toString();
//        editFragmentCallback.saveNote();

    }

    private void deleteNote(){
        //刪除
        editFragmentCallback.deleteNote(note);
    }

    public void backToHome(){
        saveNote();
        editFragmentCallback.refresh();
        getActivity().getFragmentManager().beginTransaction().remove(this).commit();
    }




    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}