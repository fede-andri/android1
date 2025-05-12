package com.example.android1.sintaxis

fun main(){
    val numero1:Int = 8;
    val numero2:Int = 1;



    fun sumar(numero1:Int, numero2:Int): Int {
        return numero1 + numero2;
    }

    val resultadoSuma:Int =sumar(numero1,numero2);
    println(resultadoSuma);
}