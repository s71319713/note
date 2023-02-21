package com.example.note.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.note.Util.Converters;
import com.example.note.dao.NoteDao;
import com.example.note.table.NoteEntity;

@Database(entities = {NoteEntity.class},version = 1)
@TypeConverters(Converters.class)
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

