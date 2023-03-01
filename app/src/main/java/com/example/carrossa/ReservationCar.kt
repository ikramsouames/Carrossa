package com.example.carrossa

import androidx.room.PrimaryKey
import java.io.Serializable

data class ReservationCar(
    val idreservation:Int,
    val datedebut: String,
    val timedebut:String,
    val matricule:String,
    val pin : String?,
    val state: String?,
    val photo : String,
    val modele : String
): Serializable
