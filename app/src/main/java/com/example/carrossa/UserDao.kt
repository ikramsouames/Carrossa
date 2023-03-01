package com.example.carrossa

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Insert
    fun addUser(user:User)

    @Query("Select * from users")
    fun getUsers():List<User>

}