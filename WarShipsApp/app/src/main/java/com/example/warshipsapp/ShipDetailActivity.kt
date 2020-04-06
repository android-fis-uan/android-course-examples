package com.example.warshipsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_ship_detail.*

class ShipDetailActivity : AppCompatActivity() {

    var shipId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ship_detail)

        shipId = intent.getStringExtra(resources.getString(R.string.SHIP_ID))
        val detailF = supportFragmentManager.findFragmentById(R.id.detailFragment) as ShipDetailFragment
        detailF.loadShip(shipId)
    }


}
