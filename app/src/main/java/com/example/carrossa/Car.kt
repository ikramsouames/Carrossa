package com.example.car

import java.io.Serializable

data class Car(
    val matricule: String,
    val photo: String,
    val marque:String,
    val modele:String,
    val type_moteur:String,
    val tarif:Int,
    val disponibilite: String,
    val capacity:Int,
    val year:Int,
    val carburant:String,
    val longitude:Double,
    val latitude:Double

): Serializable
