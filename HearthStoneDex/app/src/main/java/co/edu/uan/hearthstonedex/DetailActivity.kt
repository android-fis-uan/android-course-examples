package co.edu.uan.hearthstonedex

import android.animation.Animator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detail.*
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

/**
 * Detailed view for a card
 */
class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_detail)
        loadCard()
    }

    /**
     * Load card information
     */
    fun loadCard() {
        //val image = intent.getStringExtra("cardImage")
        val text = intent.getStringExtra("cardText")
        //var imageId = resources.getIdentifier(image,"drawable", packageName)
        val url = "https://d15f34w2p8l1cc.cloudfront.net/hearthstone/ba76f5895f734927f34cbeb6938946caaaa261d1b7cb7d54282cb34b8b810025.png"
        //cardImage.setImageResource(imageId)
        Picasso.get().setLoggingEnabled(true)
        Picasso.get()
            .load(url)
            .into(cardImage, object: Callback {
                override fun onError(e: Exception) {
                    Log.e(null,e.toString())
                }

                override fun onSuccess() {
                    // Not implemented
                }
            })

        YoYo.with(Techniques.FlipInX)
            .duration(1700)
            .withListener(object: Animator.AnimatorListener {
                override fun onAnimationRepeat(animation: Animator?) {
                }

                override fun onAnimationEnd(animation: Animator?) {
                    cardText.text = text
                }

                override fun onAnimationCancel(animation: Animator?) {
                }

                override fun onAnimationStart(animation: Animator?) {
                }

            })
            .playOn(cardImage)

    }
}
