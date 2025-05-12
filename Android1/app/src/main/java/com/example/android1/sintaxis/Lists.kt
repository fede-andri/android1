package com.example.android1.sintaxis

fun main(){
    //inmutableList();
    mutableList();
}

fun mutableList() {
    val mutableList:MutableList<String> = mutableListOf("Federico","Shamira");
    println(mutableList);

    mutableList.add("Valentin")
    println(mutableList)

    mutableList.add(0,"Facundo")
    println(mutableList)
}

private fun inmutableList() {
    val readOnly: List<String> = listOf("Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo");
    println(readOnly.size);
    println(readOnly);
    println(readOnly[0]);
    println(readOnly.last())
    println(readOnly.first())

    //Filter
    val filter = readOnly.filter { day -> day == "Lunes" }
    println(filter)

    val forEach = readOnly.forEach { day ->
        println(day);
        println(day.first()) };
}