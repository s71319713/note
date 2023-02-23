package com.example.note;

import android.content.Context;
import android.graphics.Color;
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
    private boolean longClick=false;
    private ArrayList<Note> deleteList;
    private ArrayList<Integer> positionList ;

    public RecycleViewAdapter(Context context){
        this.context = context;
        if(context instanceof ReycleviewCallback){
            noteArrayList = new ArrayList<>();
            deleteList = new ArrayList<>();
            positionList = new ArrayList<>();
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

    public ArrayList<Note> getDeleteList(){
        return  this.deleteList;
    }

    public ArrayList<Integer> getPositionList(){
        return this.positionList;
    }

    public void setIsLongClick(boolean b){
        deleteList.clear();
        positionList.clear();
        this.longClick=b;
    }


    public  class NoteViewHolder extends RecyclerView.ViewHolder {
        public ItemNoteBinding binding;
        public NoteViewHolder(ItemNoteBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(longClick){
                        Note nowClickNote = noteArrayList.get(getAdapterPosition());
                        if(!deleteList.contains(nowClickNote)){
                            Log.d("DBB", "longClick: ++"+getAdapterPosition());
                            binding.getRoot().setBackgroundResource(R.color.gainsboro);
                            deleteList.add(nowClickNote);
                            positionList.add(getAdapterPosition());

                        }else {
                            Log.d("DBB", "longClick: --"+getAdapterPosition());
                            deleteList.remove(nowClickNote);
                            positionList.remove(Integer.valueOf(getAdapterPosition()));
                            binding.getRoot().setBackgroundResource(R.color.white);

                        }

                    }else {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION && reycleviewCallback != null) {
                            reycleviewCallback.openEditView(noteArrayList.get(getAdapterPosition()));
                            Log.d("noteCallback", "onClick: " + noteArrayList.get(getAdapterPosition()));
                        }
                    }

                }
            });

            binding.getRoot().setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    reycleviewCallback.startSelectMode();
                    longClick=true;
                    return true;
                }
            });

        }
        public void bind(Note note){
            binding.content.setText(note.content);
            binding.lastupdate.setText(note.lastUpdate);
        }
    }
}
