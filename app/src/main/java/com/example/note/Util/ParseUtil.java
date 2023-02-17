package com.example.note.Util;

import androidx.lifecycle.LiveData;

import com.example.note.Entity.NoteEntity;
import com.example.note.Note;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ParseUtil {
    public static NoteEntity toEntity(Note note){
        NoteEntity noteEntity = new NoteEntity();
        noteEntity.setContent(note.getContent());
        noteEntity.setId(note.getId());
        noteEntity.setLastUpdate(note.getLastUpdate());
        return  noteEntity;
    }

    public static ArrayList<Note> toNoteList(ArrayList<NoteEntity> entityArrayList){
        ArrayList<Note> notes = new ArrayList<>();
        for (NoteEntity noteEntity : entityArrayList) {
            Note note = new Note();
            note.setContent(noteEntity.getContent());
            note.setLastUpdate(noteEntity.getLastUpdate());
            note.setId(noteEntity.getId());
            notes.add(note);
        };
        return notes;
    };
}
