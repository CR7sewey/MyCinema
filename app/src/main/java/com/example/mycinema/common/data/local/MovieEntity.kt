package com.example.mycinema.common.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(primaryKeys = ["id","category"])
data class MovieEntity(
    //@PrimaryKey
    val id: Int = 0,
    @ColumnInfo(name= "title") val title: String,
    @ColumnInfo(name= "overview") val overview: String,
    @ColumnInfo(name= "image") val image: String,
    @ColumnInfo(name= "category") val category: String,
    )

