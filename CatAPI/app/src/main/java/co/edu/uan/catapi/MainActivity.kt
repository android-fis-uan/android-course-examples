package co.edu.uan.catapi

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.koushikdutta.async.future.FutureCallback
import com.koushikdutta.ion.Ion
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    var controller: Controller? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        controller = Controller(this)
        loadCatButton.setOnClickListener { _ -> loadCatController() }
    }

    fun loadCat() {
        Ion.with(this)
            .load("http://thecatapi.com/api/images/get?format=json&size=med&results_per_page=2")
            .asString()
            .setCallback{_, data ->
                // process the data or error
                Log.v("ejemplo","Data: $data")
                val json = JSONArray(data)
                processData(json)   // you write this!
            }

    }

    fun loadCatRetrofit() {
        var retrofit = Retrofit.Builder()
            .baseUrl("http://thecatapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var catService = retrofit.create(CatAPI::class.java)
        var catCall = catService.cats()
        catCall.enqueue(object: Callback<List<Cat>>{
            override fun onFailure(call: Call<List<Cat>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Cats error", Toast.LENGTH_LONG).show()
                Log.e("CATS", t.message, t)
            }

            override fun onResponse(call: Call<List<Cat>>, response: Response<List<Cat>>) {
                Toast.makeText(this@MainActivity, "Cats loaded", Toast.LENGTH_LONG).show()
                Log.e("CATS", response.toString())
                if (response.isSuccessful()) {
                    val catsList = response.body()
                    catPictures.removeAllViews()
                    catsList?.forEach { cat ->
                        val catView = ImageView(this@MainActivity)
                        Picasso.get()
                            .load(cat.url)
                            .into(catView)
                        catPictures.addView(catView) }
                } else {
                    Log.e("CATS", response.errorBody().toString())
                }
            }
        })
    }

    fun loadCatController() {
        controller?.start()
    }

    fun processData(json: JSONArray) {
        catPictures.removeAllViews()
        for (i in 0..(json.length() - 1)) {
            val catJson = json.getJSONObject(i)
            val catView = ImageView(this)
            Picasso.get()
                .load(catJson.getString("url"))
                .into(catView)
            catPictures.addView(catView)
        }

    }
}
