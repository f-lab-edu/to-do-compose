package flab.eryuksa.todocompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import flab.eryuksa.todocompose.ui.tasks.TasksScreen
import flab.eryuksa.todocompose.ui.theme.ToDoComposeTheme

class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoComposeTheme {
                TasksScreen(viewModel())
            }
        }
    }
}
