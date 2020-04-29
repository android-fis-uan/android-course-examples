package com.example.warshipsapp.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.warshipsapp.entities.ShipEntity

@Dao
interface ShipDao {

    @Query("SELECT * FROM ships")
    fun findAll() : List<ShipEntity>

    @Query("SELECT * FROM ships WHERE ship_id_str = :id")
    fun finById(id: String) : ShipEntity

    @Insert
    fun insert(vararg ships: ShipEntity)
}