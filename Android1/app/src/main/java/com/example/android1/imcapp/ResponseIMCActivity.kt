package com.example.android1.imcapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.android1.R
import com.example.android1.imcapp.ImcAppActivity.Companion.IMC_KEY

private lateinit var tvTittleResult:TextView
private lateinit var tvResult:TextView
private lateinit var tvDescription:TextView
private lateinit var btnRecalculate:Button

class ResponseIMCActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_response_imcactivity)
        val result = intent.extras?.getDouble(IMC_KEY) ?: -1.0
        initComponents()
        initUI(result)
        initListenners()
    }

    private fun initListenners() {
        btnRecalculate.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun initUI(result: Double) {
        when(result){
            in 0.00..18.50 ->{
                setStylesAndText("Bajo", result)
            }

            in 18.51 .. 24.99 ->{
                setStylesAndText("Normal",result)
            }

            in 25.00 .. 29.99->{
                setStylesAndText("Sobre Peso",result)
            }

            in 30.00 .. 99.00 -> {
                setStylesAndText("Obesidad",result)
            }

            else-> {
                setStylesAndText("Error",result)
            }
        }
    }

    private fun setStylesAndText(tipoPeso: String, result: Double) {
        val resultadoString = result.toString()
        when(tipoPeso){
            "Bajo" -> {
                tvTittleResult.text = getString(R.string.title_peso_bajo)
                tvTittleResult.setTextColor(ContextCompat.getColor(this,R.color.peso_bajo))
                tvResult.text = resultadoString
                tvDescription.text = getString(R.string.description_peso_bajo)
            }

            "Normal" -> {
                tvTittleResult.text = getString(R.string.title_peso_normal)
                tvTittleResult.setTextColor(ContextCompat.getColor(this,R.color.peso_normal))
                tvResult.text = resultadoString
                tvDescription.text = getString(R.string.description_peso_normal)
            }

            "Sobre Peso" -> {
                tvTittleResult.text = getString(R.string.title_peso_sobre_peso)
                tvTittleResult.setTextColor(ContextCompat.getColor(this,R.color.peso_sobrepeso))
                tvResult.text = resultadoString
                tvDescription.text = getString(R.string.description_sobre_peso)
            }

            "Obesidad" -> {
                tvTittleResult.text = getString(R.string.title_peso_obesidad)
                tvTittleResult.setTextColor(ContextCompat.getColor(this,R.color.obesidad))
                tvResult.text = resultadoString
                tvDescription.text = getString(R.string.description_obesidad)
            }

            else->{
                tvTittleResult.text = getString(R.string.error)
                tvTittleResult.setTextColor(ContextCompat.getColor(this,
                    com.google.android.material.R.color.design_default_color_error))
                tvResult.text = getString(R.string.error)
                tvResult.setTextColor(ContextCompat.getColor(this,
                    com.google.android.material.R.color.design_default_color_error))
                tvDescription.text = getString(R.string.error)
                tvDescription.setTextColor(ContextCompat.getColor(this,
                    com.google.android.material.R.color.design_default_color_error))
            }
        }
    }

    private fun initComponents() {
        tvTittleResult = findViewById(R.id.tvTittleResult)
        tvResult = findViewById(R.id.tvResult)
        tvDescription = findViewById(R.id.tvDescription)
        btnRecalculate = findViewById(R.id.btnRecalculate)
    }
}