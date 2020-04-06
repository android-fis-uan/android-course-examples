package com.example.warshipsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_ship_detail.*

/**
 * A simple [Fragment] subclass.
 */
class ShipDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ship_detail, container, false)
    }

    fun loadShip(shipId: String) {
        textId.setText(shipId)
        val resourceId = resources.getIdentifier(shipId.toLowerCase()+"medium","drawable",activity?.packageName)
        imageShip.setImageResource(resourceId)
        // ...
    }

}
