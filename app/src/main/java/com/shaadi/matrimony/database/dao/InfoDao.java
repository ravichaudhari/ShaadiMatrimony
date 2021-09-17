package com.shaadi.matrimony.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.shaadi.matrimony.database.entity.InfoEntity;


@Dao
public interface InfoDao {
    @Insert
    void insertInfo(InfoEntity holder);

    @Query("Select * from info_table")
    InfoEntity getAll();

    @Query("DELETE FROM info_table")
    void deleteAll();
}
