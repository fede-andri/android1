package com.example.android1

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

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnFirstApp = findViewById<Button>(R.id.btnFirstApp)
        val btnImcApp = findViewById<Button>(R.id.btnImcApp)

        btnFirstApp.setOnClickListener {
            Log.i("MenuActivity", "Se apreto el boton de first app")
            navigateToFirstApp()
        }

        btnImcApp.setOnClickListener {
            Log.i("MenuActivity", "Se apreto el boton de imc app")
            navigateToImcApp()
        }
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