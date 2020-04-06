package com.example.warshipsapp

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_ship_list.*

/**
 * A simple [Fragment] subclass.
 */
class ShipListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ship_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        ship1.setOnClickListener { v ->
            showShip(v.tag.toString())
        }
        ship2.setOnClickListener { v ->
            showShip(v.tag.toString())
        }
        ship3.setOnClickListener { v ->
            showShip(v.tag.toString())
        }
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
}
