package com.example.mapapplication.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.mapapplication.model.Location

@Dao
interface LocationDao {

    @Insert
    fun insert(vararg location:Location)

    @Query("SELECT * FROM location")
    fun getAll(): List<Location>

    @Delete
    fun delete(location:Location)

}