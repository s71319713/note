package com.example.note.table;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.note.Note;

@Entity(tableName ="note_table")
public class NoteEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id",typeAffinity =ColumnInfo.INTEGER)
    private int id;

    @ColumnInfo(name = "content",typeAffinity =ColumnInfo.TEXT)

    private String content;

    @ColumnInfo(name = "updateDate",typeAffinity =ColumnInfo.TEXT)
    private String updateDate;

    public NoteEntity(int id, String content, String updateDate) {
        this.id = id;
        this.content = content;
        this.updateDate = updateDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public Note toNote(){
        return new Note(id,content,updateDate);
    }
}
