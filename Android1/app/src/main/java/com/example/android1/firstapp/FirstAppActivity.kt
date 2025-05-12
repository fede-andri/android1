package com.example.android1.firstapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.android1.R

class FirstAppActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_first_app)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnAceptar =findViewById<Button>(R.id.btnAceptar)
        val etName = findViewById<EditText>(R.id.etName)
        val tvErrorName = findViewById<TextView>(R.id.tvErrorName)
        btnAceptar.setOnClickListener {
            val valueEtName = etName.text.toString()

            if (valueEtName.isNotEmpty()){

                val intent = Intent(this, WelcomeActivity::class.java)
                intent.putExtra("valueEtName",valueEtName)
                startActivity(intent)
                tvErrorName.setText("")
                Log.i("Btn Aceptar","Se acepto con el texto $valueEtName")
            }else{
                tvErrorName.setText("Se necesita completar el nombre")
                Log.e("Btn Aceptar","Esta vacio el campo de texto")
            }

        }


    }
}