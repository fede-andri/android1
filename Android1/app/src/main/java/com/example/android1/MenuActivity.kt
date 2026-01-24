package com.example.android1

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.android1.firstapp.FirstAppActivity
import com.example.android1.imcapp.ImcAppActivity
import com.example.android1.superheroe.SuperHeroeListActivity
import com.example.android1.toDoApp.ToDoAppActivity
import com.example.android1.R as R1

class MenuActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R1.layout.activity_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R1.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnFirstApp = findViewById<Button>(R1.id.btnFirstApp)
        val btnImcApp = findViewById<Button>(R1.id.btnImcApp)
        val btnToDoApp = findViewById<Button>(R1.id.btnToDoApp)
        val btnHeroeList = findViewById<Button>(R1.id.btnHeroeListApp)

        btnFirstApp.setOnClickListener {
            Log.i("MenuActivity", "Se apreto el boton de first app")
            navigateToFirstApp()
        }

        btnImcApp.setOnClickListener {
            Log.i("MenuActivity", "Se apreto el boton de imc app")
            navigateToImcApp()
        }

        btnToDoApp.setOnClickListener {
            Log.i("MenuActivity", "Se apreto el boton de ToDo app")
            navigateToToDoApp()
        }

        btnHeroeList.setOnClickListener {
            Log.i("MenuActivity","Se apreto el boton de Heroe List App")
            navigateToHeroeList()
        }
    }

    private fun navigateToHeroeList() {
        val intentHeroeList = Intent(this, SuperHeroeListActivity::class.java)
        startActivity(intentHeroeList)
    }

    private fun navigateToToDoApp() {
        val intentToDoApp = Intent(this,ToDoAppActivity::class.java)
        startActivity(intentToDoApp)
    }

    private fun navigateToImcApp() {
        val intentImcApp = Intent(this,ImcAppActivity::class.java)
        startActivity(intentImcApp)
    }

    private fun navigateToFirstApp(){
        val intentFirstApp = Intent(this, FirstAppActivity::class.java)
        startActivity(intentFirstApp)
    }

}