package com.example.note;

import android.content.Context;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.note.callback.NoteCallback;
import com.example.note.databinding.ItemNoteBinding;

import java.util.ArrayList;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.NoteViewHolder> {
    Context context;
    ItemNoteBinding binding;
    ArrayList<Note> noteArrayList;
    NoteCallback noteCallback;

    public RecycleViewAdapter(Context context, ArrayList<Note> noteArrayList){
        this.context = context;
        this.noteArrayList = noteArrayList;
        if(context instanceof NoteCallback){
            this.noteCallback = (NoteCallback)context;
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

    public void addNote(Note note){
        noteArrayList.add(note);
        notifyDataSetChanged();
    }

    public void deleteNote(Note note){
        noteArrayList.remove(note);
        notifyDataSetChanged();
    }

    public void refresh() {
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
//                    if (position != RecyclerView.NO_POSITION && callback != null) {
//                        Note note = noteArrayList.get(position);
//                        callback.onItemClick(note);
//                    }
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && noteCallback != null) {
                        noteCallback.openEditView(noteArrayList.get(getAdapterPosition()));
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
