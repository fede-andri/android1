package com.example.android1.toDoApp.categories

sealed class TaskCategory(var isSelected:Boolean = true) {
    object Personal: TaskCategory()
    object Business: TaskCategory()
    object Other: TaskCategory()
}