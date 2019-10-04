package co.edu.uan.catapi

import retrofit2.Call
import retrofit2.http.GET

interface CatAPI {
    @GET("/api/images/get?format=json&size=med&results_per_page=2")
    fun cats(): Call<List<Cat>>
}
