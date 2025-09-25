package com.example.android1.toDoApp.tasks

import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android1.R
import com.example.android1.toDoApp.categories.TaskCategory

class TasksViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val tvTask:TextView = view.findViewById(R.id.tvTask)
    private val chbTask: CheckBox = view.findViewById(R.id.chbTask)

    fun render(task: Task){
        tvTask.text = task.name
    }
}