package com.example.android1.sintaxis

fun main(){
    val name = "Federico1"
    showMessage(ifBasic(name));
}

fun ifBasic(name:String):Boolean{
    if (name=="Federico"){
        return true;
    }
    return false;
}

fun showMessage(isFederico:Boolean){
    if (isFederico){
        println("Bienvenido Federico");
    }else{
        println("Sos un impostor");
    }
}