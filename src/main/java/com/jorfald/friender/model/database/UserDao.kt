package com.jorfald.friender.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jorfald.friender.model.dataClasses.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(item: User)

    @Query("SELECT * FROM table_one")
    fun getAllUsers(): List<User>

    @Query("DELETE FROM table_one WHERE id = :id")
    fun deleteUser(id: Long)
}