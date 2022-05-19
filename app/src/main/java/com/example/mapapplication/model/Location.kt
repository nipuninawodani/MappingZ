package com.example.mapapplication.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Location(
    @PrimaryKey var name: String,
    var latitude: String,
    var longitude: String
)
