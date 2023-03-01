package com.example.carrossa

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [User:: class,Trajet::class],
    version = 1)
abstract class AppDatabase : RoomDatabase(){

    abstract fun getUserDao():UserDao
    abstract fun getTrajetDao():TrajetDao
}