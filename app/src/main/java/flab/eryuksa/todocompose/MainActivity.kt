package flab.eryuksa.todocompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import flab.eryuksa.todocompose.ui.addtodo.viewmodel.AddTodoViewModel
import flab.eryuksa.todocompose.ui.addtodo.viewmodel.AddTodoViewModelFactory
import flab.eryuksa.todocompose.ui.tasks.ui.TasksScreen
import flab.eryuksa.todocompose.ui.tasks.viewmodel.TasksViewModel
import flab.eryuksa.todocompose.ui.theme.ToDoComposeTheme

class MainActivity : ComponentActivity() {

    private val tasksViewModel: TasksViewModel by viewModels()
    private val addTodoViewModel: AddTodoViewModel by viewModels {
        AddTodoViewModelFactory(tasksViewModel)
    }
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoComposeTheme {
                TasksScreen(
                    input = tasksViewModel,
                    output = tasksViewModel,
                    addTodoViewModel = addTodoViewModel
                )
            }
        }
    }
}
