package com.example.note;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.note.Model.Note;
import com.example.note.callback.ReycleviewCallback;
import com.example.note.databinding.ItemNoteBinding;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.NoteViewHolder> {
    Context context;
    ItemNoteBinding binding;
    ArrayList<Note> noteArrayList;
    ReycleviewCallback reycleviewCallback;

    public RecycleViewAdapter(Context context){
        this.context = context;
        if(context instanceof ReycleviewCallback){
            this.reycleviewCallback = (ReycleviewCallback)context;
            reycleviewCallback.getAll();
        }
    }


    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //把layout infalte視圖化
        binding = ItemNoteBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_note,parent,false));
        return new NoteViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.bind(noteArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return noteArrayList.size();
    }


    public void setData(List<Note> noteList){
        noteArrayList = (ArrayList<Note>) noteList;
        Log.d("setdata", "setData: +recycelview");
        notifyDataSetChanged();
    }


    public  class NoteViewHolder extends RecyclerView.ViewHolder {
        ItemNoteBinding binding;
        public NoteViewHolder(ItemNoteBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && reycleviewCallback != null) {
                        reycleviewCallback.openEditView(noteArrayList.get(getAdapterPosition()));
                        Log.d("noteCallback", "onClick: "+noteArrayList.get(getAdapterPosition()));
                    }

                }
            });

        }
        public void bind(Note note){
            binding.content.setText(note.content);
            binding.lastupdate.setText(note.lastUpdate);
        }
    }
}
