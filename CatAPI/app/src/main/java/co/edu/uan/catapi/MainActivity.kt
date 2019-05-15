package co.edu.uan.catapi

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.koushikdutta.async.future.FutureCallback
import com.koushikdutta.ion.Ion
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun loadCat(view: View) {
        Ion.with(this)
            .load("http://thecatapi.com/api/images/get?format=json&size=med&results_per_page=2")
            .asString()
            .setCallback{e, data ->
                // process the data or error
                Log.v("ejemplo","Data: $data")
                val json = JSONArray(data)
                processData(json)   // you write this!
            }

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
