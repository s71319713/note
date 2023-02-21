package com.example.note.Model;

import com.example.note.table.NoteEntity;

import java.io.Serializable;
import java.util.Date;

public class Note implements Serializable {
    public int id;
    public String content;
    public String lastUpdate;
    public Date date;

    public Note(){}


    public Note(int id, String content, String lastUpdate,Date date) {
        this.id = id;
        this.content = content;
        this.lastUpdate = lastUpdate;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
        return new NoteEntity(this.id,this.content,this.lastUpdate,this.date);
    }
}
