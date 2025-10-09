package com.example.android1.toDoApp.tasks

import android.content.res.ColorStateList
import android.graphics.Paint
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.android1.R
import com.example.android1.toDoApp.categories.TaskCategory

class TasksViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

    private val tvTask:TextView = view.findViewById(R.id.tvTask)
    private val chbTask: CheckBox = view.findViewById(R.id.chbTask)

    fun render(task: Task){
        tvTask.text = task.name
        when(task.category){
            TaskCategory.Business -> {
                val color = ContextCompat.getColor(view.context,R.color.todo_business_category)
                chbTask.buttonTintList = ColorStateList.valueOf(color)
            }
            TaskCategory.Other -> {
                val color = ContextCompat.getColor(view.context,R.color.todo_other_category)
                chbTask.buttonTintList = ColorStateList.valueOf(color)
            }
            TaskCategory.Personal -> {
                val color = ContextCompat.getColor(view.context,R.color.todo_personal_category)
                chbTask.buttonTintList = ColorStateList.valueOf(color)
            }

        }
        if (task.isSlected){
            tvTask.paintFlags = tvTask.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }else{
            tvTask.paintFlags = tvTask.paintFlags and  Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }

        chbTask.isChecked = task.isSlected
    }
}