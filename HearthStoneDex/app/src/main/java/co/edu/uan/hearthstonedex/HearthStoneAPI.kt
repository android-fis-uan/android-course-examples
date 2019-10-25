package co.edu.uan.hearthstonedex

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header

interface HearthStoneAPI {
    @GET("hearthstone/cards/?locale=en-US")
    fun searchCards(@Header("Authorization") token: String): Call<CardList>

    companion object Factory {
        fun getInstance(): HearthStoneAPI {
            var retrofit = Retrofit.Builder()
                .baseUrl("https://us.api.blizzard.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            var api = retrofit.create(HearthStoneAPI::class.java)
            return api
        }
    }
}