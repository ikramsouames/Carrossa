package com.example.carrossa

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface TrajetDao {
    @Insert
   fun addTrajet(trajet:Trajet)

   @Query("Select * from trajets")
   fun getTrajet():List<Trajet>

    @Delete
    fun deleteTrajet(trajet:Trajet)

    @Query("Select * from trajets where id_user =:id ")
    fun getTrajetbyid(id:Int):List<Trajet>

}