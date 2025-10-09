package com.example.android1.toDoApp

import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android1.R
import com.example.android1.toDoApp.categories.CategoriesAdapter
import com.example.android1.toDoApp.categories.TaskCategory
import com.example.android1.toDoApp.categories.TaskCategory.Personal
import com.example.android1.toDoApp.categories.TaskCategory.Business
import com.example.android1.toDoApp.categories.TaskCategory.Other
import com.example.android1.toDoApp.tasks.Task
import com.example.android1.toDoApp.tasks.TasksAdapter
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.math.log

class ToDoAppActivity : AppCompatActivity() {
    private val categories = listOf(
        Personal,
        Business,
        Other
    )

    private val tasks = mutableListOf<Task>()

    private lateinit var fabAddTask:FloatingActionButton
    //private lateinit var btnAddTask:MaterialButton
    private lateinit var rvCategorias: RecyclerView
    private lateinit var rvTasks: RecyclerView
    private lateinit var categoriesAdapter: CategoriesAdapter;
    private lateinit var tasksAdapter: TasksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_to_do_app)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setStyles()
        initComponnent()
        initUI()
        setListenners()
    }

    private fun initComponnent() {
        rvCategorias = findViewById(R.id.rvCategorias)
        rvTasks = findViewById(R.id.rvTasks)
        fabAddTask = findViewById(R.id.fabAddTask)
        //btnAddTask = findViewById(R.id.btnAddTask)
    }

    private fun initUI() {
        initAdapters()
        setRecyclerViewCategories()
        setRecyclerViewTasks()
    }

    private fun initAdapters() {
        categoriesAdapter = CategoriesAdapter(categories) { position -> onCategorySelected(position) }
        tasksAdapter = TasksAdapter(tasks) { position -> onItemSelected(position) }
    }

    private fun setRecyclerViewCategories() {
        rvCategorias.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        rvCategorias.adapter = categoriesAdapter
    }

    private fun setRecyclerViewTasks() {
        rvTasks.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rvTasks.adapter = tasksAdapter
    }

    private fun setListenners() {
        fabAddTask.setOnClickListener {
            showDialogog( )
        }
    }

    private fun showDialogog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_todo_task)
        val btnAddTask:MaterialButton = dialog.findViewById(R.id.btnAddTask)
        val etTask:EditText = dialog.findViewById(R.id.etTask)
        val rgCategory: RadioGroup = dialog.findViewById(R.id.rgCategory)

        btnAddTask.setOnClickListener {
            val selectedId = rgCategory.checkedRadioButtonId
            val selectedRadioButton:RadioButton = rgCategory.findViewById(selectedId)
            val currentEditText = etTask.text.toString()
            val currentCategory:TaskCategory = when(selectedRadioButton.text){
                "Negocio" -> Business
                "Personal" -> Personal
                else -> Other
            }
            if(currentEditText.isNotEmpty()){
                tasks.add(Task(currentEditText,currentCategory))
                updateTask()
                dialog.hide()
            }
        }
        dialog.show()
    }

    private fun onCategorySelected(position: Int){
        categories[position].isSelected = !categories[position].isSelected
        categoriesAdapter.notifyItemChanged(position)
        updateTask()
    }

    private fun onItemSelected(position:Int){
        tasks[position].isSlected = !tasks[position].isSlected
        updateTask()
    }

    private fun updateTask(){
        val selectedCategories:List<TaskCategory> = categories.filter { it.isSelected }
        val newTasks = tasks.filter { selectedCategories.contains(it.category) }
        tasksAdapter.tasks = newTasks
        tasksAdapter.notifyDataSetChanged()
    }

    private fun setStyles() {
        //Se setean los estilos de la barra de estados y de la barra de navegacion
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(
                ContextCompat.getColor(this, R.color.todo_background_todo_app)
            );
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(0);
            getWindow().setNavigationBarColor(
                ContextCompat.getColor(
                    this,
                    R.color.todo_background_todo_app
                )
            );
            // Iconos oscuros
            getWindow().getDecorView()
                .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        }
    }
}
