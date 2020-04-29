package com.example.warshipsapp.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.warshipsapp.daos.ShipDao
import com.example.warshipsapp.entities.ShipEntity

@Database(entities = arrayOf(ShipEntity::class), version = 1)
abstract class ShipsDatabase : RoomDatabase() {

    abstract fun shipDao(): ShipDao;

    companion object {
        private var _instance: ShipsDatabase? = null

        fun getDatabase(context: Context): ShipsDatabase {
            if(_instance==null) {
                _instance = Room.databaseBuilder(context,
                    ShipsDatabase::class.java, "shipsdatabase"
                ).build()
            }
            return _instance!!
        }
    }
}