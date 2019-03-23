package co.edu.uan.dictionary

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_create_definition.*

class CreateDefinition : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_definition)
    }

    fun saveDefinition(view: View) {
        // retornar la definición a la actividad principal

        // tommo los datos del formulario
        val word = editPalabra.text.toString()
        val definition = editDefinicion.text.toString()
        // creo el intent de resultado con los datos
        val resultIntent = Intent()
        resultIntent.putExtra("word", word)
        resultIntent.putExtra("definition", definition)
        // pongo el resultado de la actividad
        setResult(Activity.RESULT_OK, resultIntent)
        // finalizo la actividad actual
        finish()
    }
}
