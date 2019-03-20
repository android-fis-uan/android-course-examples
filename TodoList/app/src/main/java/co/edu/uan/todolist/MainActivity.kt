package co.edu.uan.todolist

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import java.io.File
import java.io.PrintStream

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // write short data to app-specific external storage
        val outDir = getExternalFilesDir(null)  // root dir
        val outFile = File(outDir, "example.txt")
        val output = PrintStream(outFile)
        output.println("Hello, world!")
        output.close()

// read list of pictures in external storage
        val picsDir =
            Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES)
        for ( file : picsDir.listFiles()) {
            ...
        }


    }


}
