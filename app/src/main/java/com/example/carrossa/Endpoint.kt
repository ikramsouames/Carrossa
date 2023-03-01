package com.example.carrossa

import com.example.car.Car
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

//endpoint c'est service
interface Endpoint {

    @GET("getuser/{phone_number}/{password}")
    suspend fun getuser(
        @Path("phone_number") phone_number: String,
        @Path("password") password: String
    ): Response<User?>

    @GET("getcars")
    suspend fun getcars(): Response<List<Car>>

    @GET("getreservations/{id}")
    suspend fun getreservations(@Path("id") id: Int): Response<List<ReservationCar>>

    @GET("getstate/{idreservation}")
    suspend fun getstate(@Path("idreservation") idreservation: Int): Response<State?>

    @POST("addreservation")
    suspend fun addreservation(@Body reservation: Reservation): Response<String>

    @GET("getuserbyid/{id}")
    suspend fun getuserbyid(@Path("id") id: Int): Response<User?>

    @GET("getid")
    suspend fun getid(): Response<Id>

    @Multipart
    @POST("adduser")
    suspend fun adduser(
        @Part image: MultipartBody.Part,
        @Part user: MultipartBody.Part
    ): Response<String>


    @Multipart
    @POST("updateuserbyid/{id}")
    suspend fun updateuserbyid(
        @Part id: Int,
        @Part image: MultipartBody.Part,
        @Part user: MultipartBody.Part
    ): Response<String>
}