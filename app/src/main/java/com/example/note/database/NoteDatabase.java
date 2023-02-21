package com.example.note.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.note.dao.NoteDao;
import com.example.note.table.NoteEntity;

@Database(entities = {NoteEntity.class},version = 1)
public abstract class NoteDatabase extends RoomDatabase {
    private static String  DATABASE_NAME= "note_database";

    private static NoteDatabase noteDatabase;

    public static synchronized  NoteDatabase getDBInstance(Context context){
        if(noteDatabase==null){
            noteDatabase = Room
                    .databaseBuilder(context.getApplicationContext()
                            ,NoteDatabase.class,DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
            }
        return noteDatabase;
        };


    public abstract NoteDao getNoteDao();
    }

