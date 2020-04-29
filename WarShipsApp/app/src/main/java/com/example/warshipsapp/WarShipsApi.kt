package com.example.warshipsapp

import com.example.warshipsapp.models.ShipResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface WarShipsApi {

    @POST("encyclopedia/ships/")
    fun getShips(@Query("limit") limit: Int, @Query("application_id") applicationId: String="493feb6f4e69b57312eb2e0038285a2b"): Call<ShipResponse>

    companion object {
        val API_PREFIX = "https://api.worldofwarships.ru/wows/"

        fun getService(): WarShipsApi {
            var retrofit = Retrofit.Builder()
                .baseUrl(API_PREFIX)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            var service = retrofit.create(WarShipsApi::class.java)
            return service
        }
    }
}