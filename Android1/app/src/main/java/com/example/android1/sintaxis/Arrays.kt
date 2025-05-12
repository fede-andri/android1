package com.example.android1.sintaxis

fun main(){
    val days = arrayOf("Lunes","Martes","Miercoles","Jueves","Viernes","Sabado","Domingo");
    println(days?.get(1));
    for (day in days){
        //println(day);
    }

    for (position in days.indices){
        //println(position)
    }

    for ((position,value) in days.withIndex()){
        println("$position : $value")
    }
}