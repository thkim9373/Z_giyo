package com.hoony.z_giyo.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hoony.z_giyo.db.entity.Item
import com.hoony.z_giyo.db.entity.ItemDao

@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(application: Application): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(application, AppDatabase::class.java, "item-db")
                    .build()
            }
            return INSTANCE as AppDatabase
        }
    }

    abstract val itemDao: ItemDao
}