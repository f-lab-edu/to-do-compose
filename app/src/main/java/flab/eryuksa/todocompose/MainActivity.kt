package flab.eryuksa.todocompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import flab.eryuksa.todocompose.ui.tasks.Task
import flab.eryuksa.todocompose.ui.tasks.TasksScreen
import flab.eryuksa.todocompose.ui.theme.ToDoComposeTheme

class MainActivity : ComponentActivity() {

    val todoList = listOf(Task("할 일", "", false), Task("할 일2", "", false))
    val doneList = listOf(Task("끝냈음", "", true))

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoComposeTheme {
                TasksScreen(todoList, doneList)
            }
        }
    }
}
