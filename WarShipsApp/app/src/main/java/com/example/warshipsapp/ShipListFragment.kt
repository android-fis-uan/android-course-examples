package com.example.warshipsapp

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_ship_list.*
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class ShipListFragment : Fragment() {

    var shipNames = mutableListOf<String>()

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

    fun loadShips() {
        var counter = 0
        val fileRead = Scanner(resources.openRawResource(R.raw.ships))
        fileRead.nextLine()
        while(fileRead.hasNextLine()) {
            val shipLine = fileRead.nextLine()
            val data = shipLine.split("\t")
            shipNames.add(data[0])
            if(counter++ >5) {
                break
            }
        }
        Log.e("SHIPS","Ships loaded ${shipNames}")
        fileRead.close()
    }

    fun showShips() {
        for(shipName in shipNames) {
            val shipButton = ImageButton(activity)
            /*val resId = resources.getIdentifier(shipName.toLowerCase()+"small", "drawable", activity?.packageName)
            shipButton.setImageResource(resId)*/
            Picasso.get().load("https://glossary-wows-global.gcdn.co/icons//vehicle/small/PRSC910_b0e51720e691e7e5e52ae2d5924e23224cbc0533d9f1080ac3c29f183a92fec3.png").into(shipButton)
            shipList.addView(shipButton)
//            android:tag="pasc510"
            shipButton.setTag(shipName.toLowerCase())
//            android:adjustViewBounds="true"
//            android:scaleType="fitCenter"
//            android:layout_width="0dp"
//            android:layout_height="wrap_content"
//            android:layout_columnWeight="1"
//            android:layout_gravity="fill"
            shipButton.setOnClickListener { v ->
                showShip(v.tag.toString())
            }
        }
    }
}
