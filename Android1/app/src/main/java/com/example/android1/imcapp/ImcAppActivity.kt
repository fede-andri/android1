package com.example.android1.imcapp

import android.icu.text.DecimalFormat
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.android1.R
import com.google.android.material.slider.RangeSlider

class ImcAppActivity : AppCompatActivity() {
    private var isViewCardMaleSelected:Boolean = true
    private var isViewCardFemaleSelected:Boolean = true
    private lateinit var viewCardMale:CardView;
    private lateinit var viewCardFemale:CardView;
    private lateinit var tvHeight:TextView;
    private lateinit var rsHeight:RangeSlider;

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
        initListenners()
    }

    private fun initComponent() {
        viewCardMale = findViewById(R.id.cardMale)
        viewCardFemale = findViewById(R.id.cardFemale)
        tvHeight = findViewById(R.id.tvAltura)
        rsHeight = findViewById(R.id.rsHeight)
    }

    private fun initListenners() {
        initListenerCard(viewCardMale,true,false)
        initListenerCard(viewCardFemale,false,true)
        initListenerMoveRange()
    }

    private fun initListenerMoveRange() {
        rsHeight.addOnChangeListener { slider, value, fromUser ->
            val decimalFormat = DecimalFormat("#.##")
            val result = decimalFormat.format(value)
            tvHeight.text = "$result cm"
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