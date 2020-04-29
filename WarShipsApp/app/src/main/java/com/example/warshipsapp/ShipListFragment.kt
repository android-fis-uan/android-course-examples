package com.example.warshipsapp

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.example.warshipsapp.databases.ShipsDatabase
import com.example.warshipsapp.entities.ShipEntity
import com.example.warshipsapp.models.Ship
import com.example.warshipsapp.models.ShipResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_ship_list.*
import retrofit2.Call
import retrofit2.Response
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class ShipListFragment : Fragment() {
    var ships : List<ShipEntity>? = mutableListOf()
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

    fun loadShips() {

        val service = WarShipsApi.getService()
        service.getShips(20).enqueue(object: retrofit2.Callback<ShipResponse> {
            override fun onFailure(call: Call<ShipResponse>, t: Throwable) {
                Log.e("SHIPS","Error loading ships API", t)
            }

            override fun onResponse(call: Call<ShipResponse>, response: Response<ShipResponse>) {
                val data = response.body()?.data
                saveShips(data?.values!!)
            }

        })
    }

    fun saveShips(ships: Collection<Ship>) {
        val shipss = ships
        SaveShips(this).execute(*shipss.toTypedArray())
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
    fun loadShipsFile() {
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
        for(ship in ships!!) {
            val shipButton = ImageButton(activity)
            //val resId = resources.getIdentifier(shipName.toLowerCase()+"small", "drawable", activity?.packageName)
            val url = ship.images_small
            Picasso.get()
                .setIndicatorsEnabled(true)
            Picasso
                .get()
                .load(url)
                .resize(440,220)
                .centerCrop()
                .into(shipButton)
            shipList.addView(shipButton)
            shipButton.setTag(ship.ship_id_str.toLowerCase())
            shipButton.setOnClickListener { v ->
                showShip(v.tag.toString())
            }
            i++
        }

    }

    companion object {
        // Create a task for your event
        class SaveShips(val fragment: ShipListFragment) :
            AsyncTask<Ship, Void, Void>() {
            override fun doInBackground(vararg params: Ship): Void? {
                val db = ShipsDatabase.getDatabase(fragment.activity!!)
                for(ship in params) {
                    val shipEntity = ShipEntity(
                        ship.ship_id_str,
                        ship.name,
                        ship.type,
                        ship.nation,
                        ship.images.get("small")!!,
                        ship.description
                    )
                    if(db.shipDao().finById(ship.ship_id_str)==null)
                        db.shipDao().insert(shipEntity)
                }
                fragment.ships = db.shipDao().findAll()
                return null
            }
            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)
                fragment.showShips()
            }
        }

        class LoadShips(val fragment: ShipListFragment) :
            AsyncTask<Void, Void, Boolean>() {
            override fun doInBackground(vararg params: Void): Boolean {
                val db = ShipsDatabase.getDatabase(fragment.activity!!)
                val ships = db.shipDao().findAll()
                fragment.ships = ships
                return ships.size>0
            }
            override fun onPostExecute(result: Boolean) {
                super.onPostExecute(result)
                if(result)
                    fragment.showShips()
                else
                    fragment.loadShips()
            }
        }

    }
}
