package com.example.qrapp.data.source

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductEntity(

    @PrimaryKey(autoGenerate = true) @ColumnInfo val id: Long?,
    @ColumnInfo val sn: String,
    @ColumnInfo val name: String,
    @ColumnInfo val image: Int,
    @ColumnInfo var isChecked: Boolean
) {
    constructor(sn: String, name: String, image: Int, isChecked: Boolean) : this(
        null,
        sn,
        name,
        image,
        isChecked
    )
}
