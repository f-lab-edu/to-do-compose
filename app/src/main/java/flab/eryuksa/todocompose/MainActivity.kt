package flab.eryuksa.todocompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import flab.eryuksa.todocompose.ui.tasks.Task
import flab.eryuksa.todocompose.ui.tasks.TasksScreen
import flab.eryuksa.todocompose.ui.theme.Padding
import flab.eryuksa.todocompose.ui.theme.ToDoComposeTheme

class MainActivity : ComponentActivity() {

    val todoList = listOf(Task("할 일", "", false), Task("할 일2", "", false))
    val doneList = listOf(Task("끝냈음", "", true))

    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoComposeTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    modifier = Modifier.fillMaxSize().padding(Padding.MEDIUM),
                    floatingActionButton = {
                        FloatingActionButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Rounded.Add,
                                contentDescription = stringResource(R.string.add_task)
                            )
                        }
                    }
                ) {
                    TasksScreen(todoList, doneList)
                }
            }
        }
    }
}
