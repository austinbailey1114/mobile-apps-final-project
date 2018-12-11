package com.example.austinbailey.final_project_austin_bailey;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {LodgeRoom.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase{
    public abstract LodgeRoomDao lodgeRoomDao();
}
