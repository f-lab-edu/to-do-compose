package flab.eryuksa.todocompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import flab.eryuksa.todocompose.ui.addtask.AddTaskScreen
import flab.eryuksa.todocompose.ui.theme.ToDoComposeTheme

class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoComposeTheme {
                // TasksScreen(todoList, doneList)
                AddTaskScreen(
                    onDismissRequest = {},
                    onClickAdd = {},
                    onClickCancel = {}
                )
            }
        }
    }
}
