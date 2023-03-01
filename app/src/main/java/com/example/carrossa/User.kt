package com.example.carrossa

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "users")
data class User(
    @PrimaryKey
    val id:Int,
    val full_name:String,
    val phone_number : String,
    val password:String?,
    val credit_card: Int,
    val validity_card:String?,
    val profil_pic:String?,
    val driving_licence:String

): Serializable
