package com.example.note;

import java.io.Serializable;

public class Note implements Serializable {
    String content;
    String lastUpdate;

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

    @Override
    public String toString() {
        return "Note{" +
                "content='" + content + '\'' +
                ", lastUpdate='" + lastUpdate + '\'' +
                '}';
    }
}
