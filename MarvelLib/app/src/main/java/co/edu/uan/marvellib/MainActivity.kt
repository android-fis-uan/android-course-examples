package co.edu.uan.marvellib

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onMovieClick(view: View) {
        val movie = view as ImageView
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("MOVIE_NAME", movie.tag.toString())
        startActivity(intent)
    }
}
