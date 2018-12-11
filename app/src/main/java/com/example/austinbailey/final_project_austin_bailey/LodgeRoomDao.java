package com.example.austinbailey.final_project_austin_bailey;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface LodgeRoomDao {
    @Query("SELECT * from lodgeroom")
    List<LodgeRoom> getAll();

    @Insert
    void insertOne(LodgeRoom lodgeRoom);

    @Query("DELETE FROM lodgeroom WHERE id = :id")
    void deleteLodge(int id);
}
