package com.yusuf.customerapp

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import java.text.NumberFormat

@Entity(tableName = "customer_table")
data class Customer(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "customer_Id")
    var id: Int = 0,

    @ColumnInfo(name = "first_name")
    val firstName: String,

    @ColumnInfo(name = "last_name")
    val lastName: String,

//    @ColumnInfo(name = "date_created")
//    val dataCreated: String,
    )
{
    constructor(id :String, firstName :String, lastName :String)
     : this(0, firstName, lastName)
}

data class NameTuple(
    @ColumnInfo(name = "first_name") val firstName: String?,
    @ColumnInfo(name = "last_name") val lastName: String?
)