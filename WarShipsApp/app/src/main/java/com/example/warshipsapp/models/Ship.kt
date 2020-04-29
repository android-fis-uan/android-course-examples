package com.example.warshipsapp.models

class Ship (
    var ship_id_str: String,
    var name: String,
    var type: String,
    var nation: String,
    var images: Map<String,String>,
    var description: String
)

class ShipResponse(
    val status: String,
    val data: Map<String,Ship>
)

//class ShipImages (
//    val small: String,
//    val medium: String
//)