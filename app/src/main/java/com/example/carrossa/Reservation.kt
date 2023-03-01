package com.example.carrossa

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*
@Entity(tableName = "reservation")
data class Reservation (

    @PrimaryKey
    val idreservation:Int?,
    var datedebut: String,
    val datefin:String,
    val timedebut:String,
    val timefin:  String,
    val cout : Int?,
    val pin : String,
    var id:Int,
    val matricule:String
): Serializable

