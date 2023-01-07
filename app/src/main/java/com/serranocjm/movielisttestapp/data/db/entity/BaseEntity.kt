package com.serranocjm.movielisttestapp.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

abstract class BaseEntity {
    @ColumnInfo(name = "table_id")
    @PrimaryKey(autoGenerate = true)
    var tableId: Long = 0
}
