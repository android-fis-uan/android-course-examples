package co.edu.uan.tmnt

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    val myArray = ArrayList<String>()
    var myAdapter : ArrayAdapter<String>  ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initList()

        characterList.setOnItemClickListener { _, _, position, id ->
            Toast.makeText(this, "You clicked item $position with id $id", Toast.LENGTH_SHORT).show()
            println("You clicked item $position with id $id")
            showTurtle(position)
            myArray.add("Hello, I did click on position $position")
            myAdapter?.notifyDataSetChanged()
        }
    }

    fun initList() {

        myArray.add("Leo")
        myArray.add("Mike")
        myArray.add("Donatello")
        myArray.add("Raphael")

        myAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, myArray)

        characterList.adapter = myAdapter

    }

    fun radioButtonClick(view: View) {
        val position =  when (view) {

            rb_leo -> 0
            rb_mike -> 1
            rb_don -> 2
            else -> 3
        }
        showTurtle(position)
    }

    fun showTurtle(position: Int) {
        val id = when (position) {

            0 -> R.drawable.tmntleo
            1 -> R.drawable.tmntmike
            2 -> R.drawable.tmntdon
            else -> R.drawable.tmntraph
        }
        turtle.setImageResource(id)
    }
    
}
