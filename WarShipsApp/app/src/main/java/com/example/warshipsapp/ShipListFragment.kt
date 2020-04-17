package com.example.warshipsapp

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.koushikdutta.async.future.FutureCallback
import com.koushikdutta.ion.Ion
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_ship_list.*
import java.lang.Exception
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class ShipListFragment : Fragment() {

    var shipNames = mutableListOf<String>()
    var shipThumbnails = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ship_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loadShips()
        showShips()
//        ship1.setOnClickListener { v ->
//            showShip(v.tag.toString())
//        }
//        ship2.setOnClickListener { v ->
//            showShip(v.tag.toString())
//        }
//        ship3.setOnClickListener { v ->
//            showShip(v.tag.toString())
//        }
    }

    fun showShip(ship: String) {
        if (activity?.getResources()?.getConfiguration()?.orientation ==
            Configuration.ORIENTATION_LANDSCAPE) {
            // update another fragment within this same activity
            val detailF = activity?.supportFragmentManager?.findFragmentById(R.id.shipDetailFragment) as ShipDetailFragment
            detailF.loadShip(ship)
        }
        else {
            val openIntent = Intent(activity, ShipDetailActivity::class.java)
            openIntent.putExtra(resources.getString(R.string.SHIP_ID), ship)
            startActivity(openIntent)
        }
    }

    /*
    0: id
    1: name
    2: type
    3: nation
    4: img_small
    5: img_medium
    6: description
     */
    fun loadShips() {
        var counter = 0
        val fileRead = Scanner(resources.openRawResource(R.raw.ships))
        fileRead.nextLine()
        while(fileRead.hasNextLine()) {
            val shipLine = fileRead.nextLine()
            val data = shipLine.split("\t")
            shipNames.add(data[0])
            shipThumbnails.add(data[4])
            if(counter++ >40) {
                break
            }
        }
        Log.e("SHIPS","Ships loaded ${shipNames}")
        fileRead.close()
    }

    fun showShips() {
        var i = 0
        for(shipName in shipNames) {
            val shipButton = ImageButton(activity)
            //val resId = resources.getIdentifier(shipName.toLowerCase()+"small", "drawable", activity?.packageName)
            val url = shipThumbnails[i]
            Picasso.get()
                .setIndicatorsEnabled(true)
            Picasso
                .get()
                .load(url)
                .resize(440,220)
                .centerCrop()
                .into(shipButton)
            shipList.addView(shipButton)
            YoYo.with(Techniques.RotateIn)
                .duration(700)
                .repeat(5)
                .playOn(shipButton);
            shipButton.setTag(shipName.toLowerCase())
            shipButton.setOnClickListener { v ->
                showShip(v.tag.toString())
            }
            i++
        }

        Ion.with(this)
            .load("https://api.thecatapi.com/v1/images/search?limit=5&page=10&order=Desc")
            .asJsonArray()
            .setCallback(object: FutureCallback<JsonArray>{
                override fun onCompleted(e: Exception?, result: JsonArray?) {
                    if(result!=null) {
                        val a = result[0] as JsonObject
                        Log.v("SHIPSS",a.get("url").asString)
                    }

                }

            })
    }
}
