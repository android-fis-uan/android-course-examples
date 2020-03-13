package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    //val numero: Int = (0+Math.random()*100).toInt()
    val numero : Int = Random.nextInt(0,100)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun doClick(view: View) {
        Toast.makeText(this,"Hola click, el numero es ${texto.text.toString()}",Toast.LENGTH_LONG).show()
        println("Hello")
    }
}
