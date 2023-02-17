package com.example.note;

import java.io.Serializable;

public class Note implements Serializable {
    String content;
    String lastUpdate;
    private int id;

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

    public void setId(int id){ this.id=id; };

    @Override
    public String toString() {
        return "Note{" +
                "content='" + content + '\'' +
                ", lastUpdate='" + lastUpdate + '\'' +
                ", id=" + id +
                '}';
    }
}
