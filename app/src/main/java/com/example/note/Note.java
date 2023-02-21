package com.example.note;

import com.example.note.table.NoteEntity;

import java.io.Serializable;

public class Note implements Serializable {
    int id;
    String content;
    String lastUpdate;

    public Note(){}


    public Note(int id, String content, String lastUpdate) {
        this.id = id;
        this.content = content;
        this.lastUpdate = lastUpdate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Note{" +
                "content='" + content + '\'' +
                ", lastUpdate='" + lastUpdate + '\'' +
                '}';
    }

    public NoteEntity toEntity(){
        return new NoteEntity(this.id,this.content,this.lastUpdate);
    }
}
