package com.example.note.callback;

import com.example.note.table.NoteEntity;

import java.util.List;

public interface NoteRepositoryCallback {
    void setData(List<NoteEntity> entityList);
}
