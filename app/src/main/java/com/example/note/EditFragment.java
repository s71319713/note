package com.example.note;

import static com.example.note.Util.Constant.EDIT;

import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.example.note.Model.Note;
import com.example.note.Util.TimeUtils;
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
        //當你將 attachToRoot 參數設置為 true 時，
        // 表示要將充氣出來的佈局直接附加到 root 容器中，
        // 而不是返回一個不依附於 root 容器的 View。
        // 因此，如果你將 attachToRoot 設置為 true，
        // 返回的視圖將成為 root 容器的子視圖。如果 attachToRoot 設置為 false，
        // 則返回的視圖僅表示佈局文件的層次結構，而不會與任何父視圖關聯。
        //
        //如果你將 attachToRoot 參數設置為 true，並且你已經有一個視圖容器，
        // 那麼在添加新的視圖之前，你需要先從視圖容器中移除已有的視圖，否則新的視圖將與已有的視圖疊加。
        // 因此，當你設置 attachToRoot 參數時，需要注意它的值和容器中是否已經存在視圖的情況
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
        if(note.content.equals(binding.edittext.getText().toString())){
            return;
        }
        note.content = binding.edittext.getText().toString();
        note.lastUpdate = TimeUtils.getCurrentTime();
        note.date = TimeUtils.getCurrentDate();
    }

    private void deleteNote(){
        //刪除
        editFragmentCallback.deleteNote(note);
    }

    public void backToHome(){
        saveNote();
        editFragmentCallback.updateNote(note);
        hideKeyboard();
        getActivity().getFragmentManager().beginTransaction().remove(this).commit();
    }

    private void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(
                Context.INPUT_METHOD_SERVICE);
        View focusedView = getActivity().getCurrentFocus();

        if (focusedView != null) {
             inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }




    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        saveNote();
        editFragmentCallback.updateNote(note);
    }

}