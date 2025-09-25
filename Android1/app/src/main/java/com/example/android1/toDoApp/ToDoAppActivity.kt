package com.example.android1.toDoApp

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android1.R
import com.example.android1.toDoApp.categories.CategoriesAdapter
import com.example.android1.toDoApp.categories.TaskCategory.Personal
import com.example.android1.toDoApp.categories.TaskCategory.Business
import com.example.android1.toDoApp.categories.TaskCategory.Other
import com.example.android1.toDoApp.tasks.Task
import com.example.android1.toDoApp.tasks.TasksAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.math.log

class ToDoAppActivity : AppCompatActivity() {
    private val categories = listOf(
        Personal,
        Business,
        Other
    )

    private val tasks = mutableListOf(
        Task("Ordenar",Personal, false),
        Task("Estudiar",Business, false),
        Task("Leer",Personal, false),
        Task("Leer",Personal, false),
        Task("Leer",Other, false),
        Task("Leer",Other, false),
        Task("Leer",Business, false),
        Task("Leer",Business, false),
        Task("Leer",Personal, false),
        Task("Cocinar",Other, false)
    )

    private lateinit var fabAddTask:FloatingActionButton
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
    }

    private fun initUI() {
        initAdapters()
        setRecyclerViewCategories()
        setRecyclerViewTasks()
    }

    private fun initAdapters() {
        categoriesAdapter = CategoriesAdapter(categories)
        tasksAdapter = TasksAdapter(tasks)
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
            Log.i("todoApp","Se presiono el boton de Add Task")
        }
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
