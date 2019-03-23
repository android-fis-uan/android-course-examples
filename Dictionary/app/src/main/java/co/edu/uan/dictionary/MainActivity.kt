package co.edu.uan.dictionary

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class MainActivity : AppCompatActivity() {

    val wordList = ArrayList<String>()
    val definitions = HashMap<String,String>()
    val CREATE_DIALOG = 123

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initList()
    }

    fun initList() {
        // cargar la informacion del archivo en la lista

        val scanner = Scanner(resources.openRawResource(R.raw.dictionary))
        while(scanner.hasNextLine()) {
            val line = scanner.nextLine()
            val defs = line.split(";")
            val word = defs[0]
            val definition = defs[1]
            wordList.add(word)
            definitions[word] = definition
        }

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, wordList)
        dictionaryList.adapter = adapter

        dictionaryList.setOnItemClickListener { parent, view, position, id ->
            showDefinition(wordList[position])
        }
    }

    fun showDefinition(definition: String) {
        // open second activity
        val intent : Intent = Intent(this, DetailActivity::class.java)
        val textParameter = definitions[definition]
        intent.putExtra("definition", textParameter)
        startActivity(intent)
    }

    fun openCreationDialog(view: View) {
        val intent = Intent(this, CreateDefinition::class.java)
        startActivityForResult(intent, CREATE_DIALOG)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == CREATE_DIALOG) {
            // en este caso el resultado viene de la actividad de creación
            val word: String = data?.getStringExtra("word")  ?: "default"
            val definition = data?.getStringExtra("definition") ?: "default"
            Toast.makeText(this, "${word} : ${definition}", Toast.LENGTH_SHORT).show()
            wordList.add(word)
            definitions[word] = definition
        }
    }

}
