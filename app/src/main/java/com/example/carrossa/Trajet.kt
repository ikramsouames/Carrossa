package com.example.carrossa

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "trajets")
data class Trajet(
    @PrimaryKey
    val id_trajet:Int,
    val date: String,
    val adr_depart : String,
    val adr_ariv : String,
    val timedebut:String,
    val timefin:  String,
    val cout : String,
    val id_reservation:Int,
    val id_user:Int,
    )
//,foreignKeys = [
//        ForeignKey(entity=Reservation::class,
//            parentColumns=["idreservation"],childColumns = ["id_reservation"]
//
//    )]