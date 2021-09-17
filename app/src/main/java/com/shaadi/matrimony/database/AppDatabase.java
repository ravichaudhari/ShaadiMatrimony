package com.shaadi.matrimony.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.shaadi.matrimony.database.dao.AppDao;
import com.shaadi.matrimony.database.dao.InfoDao;
import com.shaadi.matrimony.database.entity.InfoEntity;
import com.shaadi.matrimony.holder.Result;

@Database(entities = {InfoEntity.class, Result.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;       //Singleton

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "shaadi_db")
                            .fallbackToDestructiveMigration() // it will delete database with all tables in case of no migration
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract InfoDao infoDao();

    public abstract AppDao appDao();

}
