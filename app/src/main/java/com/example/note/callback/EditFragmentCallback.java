package com.example.note.callback;

import com.example.note.Note;

public interface EditFragmentCallback {
    void deleteNote(Note note);
    void refresh();
}
