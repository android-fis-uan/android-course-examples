package co.edu.uan.marvellib

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_detail.*
import java.util.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val movieName = intent.getStringExtra("MOVIE_NAME")
        Log.v("MARVELLOG","Loading movie ${movieName}")
        setMovie(movieName)
    }

    fun setMovie(movieName: String) {
        val imageId = resources.getIdentifier(movieName,"drawable", packageName)
        val fileId = resources.getIdentifier(movieName,"raw", packageName)
        var movieTitle = ""
        var movieDescription = ""
        val fileStream = Scanner(resources.openRawResource(fileId))
        if(fileStream.hasNextLine())
            movieTitle = fileStream.nextLine()
        while(fileStream.hasNextLine()) {
            movieDescription += fileStream.nextLine() + "\n"

        }
        fileStream.close()
        movie_image.setImageResource(imageId)
        movie_name.setText(movieTitle)
        movie_description.setText(movieDescription)
    }
}
