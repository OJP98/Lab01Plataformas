package com.oscarjuarez.proyecto1;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by Oscar Juarez on 28/02/2018.
 */

@Database(entities = Contacto.class , version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DATABASE_NAME="Database-Room";

    public abstract ContactDao contactDao();

    private static AppDatabase mInstance;

    public static AppDatabase getInstance(Context context) {

        if(mInstance==null){
            mInstance = Room.databaseBuilder(context, AppDatabase.class,DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return mInstance;
    }

    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }
}
