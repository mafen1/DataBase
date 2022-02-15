package com.example.room.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "userTable")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val name: String,
    val female:String,
    val age: Int
): Parcelable
