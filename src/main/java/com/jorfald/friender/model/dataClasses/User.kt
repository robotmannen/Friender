package com.jorfald.friender.model.dataClasses

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jorfald.friender.model.dataClasses.UserLocation
import com.jorfald.friender.model.dataClasses.Employment

@Entity(tableName = "table_one")
data class User(
    @PrimaryKey
    val id: Long,
    val first_name: String,
    val last_name: String,
    val email: String,
    val gender: String,
    val date_of_birth: String,

    @Embedded
    val employment: Employment?,

    @Embedded
    val address: UserLocation,

    var imageUrl: String?
)


