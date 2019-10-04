package co.edu.uan.catapi

import android.util.Log
import android.widget.ImageView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

class Controller(act: MainActivity): Callback<List<Cat>> {

    val BASE_URL = "http://thecatapi.com/"

    val activity = act

    fun start() {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val catAPI = retrofit.create<CatAPI>(CatAPI::class.java!!)

        val call = catAPI.cats()

        call.enqueue(this)

    }
    override fun onFailure(call: Call<List<Cat>>, t: Throwable) {
        Log.e("CATS", t.message, t)
    }

    override fun onResponse(call: Call<List<Cat>>, response: Response<List<Cat>>) {
        if (response.isSuccessful()) {
            val catsList = response.body()
            activity.catPictures.removeAllViews()
            catsList?.forEach { cat -> addCat(cat.url) }
        } else {
            Log.e("CATS", response.errorBody().toString())
        }
    }

    fun addCat(url: String) {
        val catView = ImageView(activity)
        Picasso.get()
            .load(url)
            .into(catView)
        activity.catPictures.addView(catView)
    }

}