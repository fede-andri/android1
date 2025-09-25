package com.example.android1.toDoApp.tasks

import com.example.android1.toDoApp.categories.TaskCategory

data class Task(val name:String, val category: TaskCategory, var isSlected:Boolean = false) {

}