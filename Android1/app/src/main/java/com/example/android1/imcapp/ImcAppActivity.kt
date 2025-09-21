package com.example.android1.imcapp

import android.annotation.SuppressLint
import android.content.Intent
import android.icu.text.DecimalFormat
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.android1.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import kotlin.math.log

class ImcAppActivity : AppCompatActivity() {
    private var isViewCardMaleSelected:Boolean = true
    private var isViewCardFemaleSelected:Boolean = true
    private var currentWeight:Int = 55
    private var currentEdad: Int = 26
    private var currentHeigh: Int = 120
    private lateinit var viewCardMale:CardView;
    private lateinit var viewCardFemale:CardView;
    private lateinit var tvHeight:TextView;
    private lateinit var rsHeight:RangeSlider;
    private lateinit var btnSubtractWeight:FloatingActionButton;
    private lateinit var btnPlusWeight:FloatingActionButton;
    private lateinit var tvPeso:TextView
    private lateinit var btnSubtractEdad:FloatingActionButton
    private lateinit var btnPlusEdad:FloatingActionButton
    private lateinit var tvEdad:TextView
    private lateinit var btnCalculate:Button

    companion object{
        const val IMC_KEY = "IMC_RESULT"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_imc_app)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponent()
        initValues()
        initListenners()
    }

    private fun initComponent() {
        viewCardMale = findViewById(R.id.cardMale)
        viewCardFemale = findViewById(R.id.cardFemale)
        tvHeight = findViewById(R.id.tvAltura)
        rsHeight = findViewById(R.id.rsHeight)
        btnPlusWeight = findViewById(R.id.btnPlusWeight)
        btnSubtractWeight = findViewById(R.id.btnSubtractWeight)
        tvPeso = findViewById(R.id.tvPeso)
        btnPlusEdad = findViewById(R.id.btnPlusEdad)
        btnSubtractEdad = findViewById(R.id.btnSubtractEdad)
        tvEdad = findViewById(R.id.tvEdad)
        btnCalculate = findViewById(R.id.btnCalculate)
    }

    private fun initValues() {
        tvPeso.text = currentWeight.toString()
        tvEdad.text = currentEdad.toString()
        tvHeight.text = "$currentHeigh cm"
    }

    private fun initListenners() {
        initListenerCard(viewCardMale,true,false)
        initListenerCard(viewCardFemale,false,true)
        initListenerMoveRange()
        initListennerClickButtonPlusOrSubstract(btnPlusWeight,true, tvPeso,true)
        initListennerClickButtonPlusOrSubstract(btnSubtractWeight,false,tvPeso,true)
        initListennerClickButtonPlusOrSubstract(btnPlusEdad,true, tvEdad,false)
        initListennerClickButtonPlusOrSubstract(btnSubtractEdad,false,tvEdad,false)
        initListennerCalculate(btnCalculate)
    }

    private fun initListennerCalculate(btnCalculate: Button) {
        btnCalculate.setOnClickListener {
            val result = calculateIMC()
            navigateToResponseIMC(result)
        }
    }

    private fun navigateToResponseIMC(result: Double) {
        val intent = Intent(this, ResponseIMCActivity::class.java)
        intent.putExtra(IMC_KEY,result)
        startActivity(intent)
    }

    private fun calculateIMC(): Double {
        val df = DecimalFormat("#.##")
        val imc = currentWeight/(currentHeigh.toDouble()/100 * currentHeigh.toDouble()/100)
        return df.format(imc).toDouble()
    }

    private fun initListennerClickButtonPlusOrSubstract(btn: FloatingActionButton, isPlus: Boolean, textView:TextView, isWeight:Boolean) {
       btn.setOnClickListener {
           if (isPlus){
                this.plus(textView,isWeight)
           }else{
               this.substract(textView,isWeight)
           }
       }
    }

    private fun substract(textView: TextView,isWeight:Boolean) {
        val currentValue:Int
        if (isWeight){
            if (currentWeight>=1){
                this.currentWeight -= 1
            }
            currentValue = currentWeight
        }else{
            if (currentEdad>=1){
                this.currentEdad -= 1
            }
            currentValue = currentEdad
        }
        setTextView(textView, currentValue)
    }

    private fun setTextView(textView: TextView, currentValue: Int) {
        textView.text = currentValue.toString()
    }

    private fun plus(textView: TextView, isWeight:Boolean) {
        val currentValue:Int
        if (isWeight){
            this.currentWeight += 1
            currentValue = currentWeight
        }else{
            this.currentEdad += 1
            currentValue = currentEdad
        }
        setTextView(textView, currentValue)
    }

    private fun initListenerMoveRange() {
        rsHeight.addOnChangeListener { slider, value, fromUser ->
            val decimalFormat = DecimalFormat("#.##")
            currentHeigh = decimalFormat.format(value).toInt()
            tvHeight.text = "$currentHeigh cm"
        }
    }

    private fun initListenerCard(viewCard: CardView, isViewCardMaleSelected:Boolean, isViewCardFemaleSelected:Boolean) {
        viewCard.setOnClickListener{
            setCardSelected(isViewCardMaleSelected,isViewCardFemaleSelected)
            setGenderColor()
        }
    }

    private fun setCardSelected(isViewCardMaleSelected:Boolean,isViewCardFemaleSelected:Boolean){
        this.isViewCardMaleSelected = isViewCardMaleSelected
        this.isViewCardFemaleSelected = isViewCardFemaleSelected
    }

    private fun setGenderColor() {
        viewCardMale.setCardBackgroundColor(getBackgroudColor(isViewCardMaleSelected))
        viewCardFemale.setCardBackgroundColor(getBackgroudColor(isViewCardFemaleSelected))
    }
    private fun getBackgroudColor(selectedView: Boolean): Int {
        val backgroundColorReference = if (selectedView){
            R.color.background_component_selected
        }else{
            R.color.background_component
        }
        return ContextCompat.getColor(this,backgroundColorReference)
    }
}