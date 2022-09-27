package com.todo.todoappcompose.database


import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Suppress("DEPRECATED_ANNOTATION")
    @Parcelize
    @Entity(
        tableName = "deviceapps",
        indices = [Index(value = ["title"], unique = true)]
    )
data class DeviceApps(
    @ColumnInfo(name = "title")
    var title: String? = "",
    @ColumnInfo(name = "desc")
    var desc: String? = "",
) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}