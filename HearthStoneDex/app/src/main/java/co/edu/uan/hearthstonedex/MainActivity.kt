package co.edu.uan.hearthstonedex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_detail.view.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

/**
 * Main Activity
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
        drawCards()
    }

    private fun drawCards() {
        val cardList = readCardsFile()
        for(card in cardList) {
            addCard(card)
        }
    }

    private fun addCard(card: String) {
        val ib = ImageButton(this)
        var imageId = resources.getIdentifier(getCardCropImage(card),"drawable", packageName)
        if(imageId==0) {
            imageId = resources.getIdentifier("innervate_crop","drawable", packageName)
        }
        ib.setImageResource(imageId)
        ib.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("cardImage",getCardImage(card))
            intent.putExtra("cardText",getCardText(card))
            startActivity(intent)
        }
        cardsLayout.addView(ib)
        val tv = TextView(this)
        tv.text = getCardName(card)
        cardsLayout.addView(tv)
    }

    private fun readCardsFile() : ArrayList<String> {
        // Open the cards file as a raw resource
        val inputStream = resources.openRawResource(R.raw.base_cards)
        val scanner = Scanner(inputStream)
        // Read all the lines of the file
        val cardsList = ArrayList<String>()
        scanner.nextLine()
        while(scanner.hasNextLine()) {
            val line = scanner.nextLine()
            cardsList.add(line)
        }
        scanner.close()
        return cardsList
    }

    private fun getCardName(card: String) : String {
        val cols = card.split(",")
        return cols[1]
    }

    private fun getCardCropImage(card: String) : String {
        val cols = card.split(",")
        return cols[2]
    }

    private fun getCardImage(card: String) : String {
        val cols = card.split(",")
        return cols[3]
    }

    private fun getCardText(card: String) : String {
        val cols = card.split(",")
        return cols[5]
    }
}
