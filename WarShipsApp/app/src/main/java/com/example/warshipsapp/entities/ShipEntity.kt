package com.example.warshipsapp.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ships")
data class ShipEntity (
    @PrimaryKey var ship_id_str: String,
    @ColumnInfo(name="name") var name: String,
    @ColumnInfo(name="type") var type: String,
    @ColumnInfo(name="nation") var nation: String,
    @ColumnInfo(name="image") var images_small: String,
    @ColumnInfo(name="description") var description: String
)
