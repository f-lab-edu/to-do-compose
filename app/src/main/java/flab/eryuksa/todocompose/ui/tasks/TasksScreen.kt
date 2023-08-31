package flab.eryuksa.todocompose.ui.tasks

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import flab.eryuksa.todocompose.R
import flab.eryuksa.todocompose.ui.theme.Padding
import flab.eryuksa.todocompose.ui.theme.ToDoComposeTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize().padding(Padding.LARGE),
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = stringResource(R.string.add_task)
                )
            }
        }
    ) {
        NoTask()
    }
}

@Composable
fun TaskItem(task: Task, onTaskCheckedChange: (Boolean) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = Padding.LARGE,
                vertical = Padding.MEDIUM
            )
    ) {
        Checkbox(
            checked = task.isDone,
            onCheckedChange = onTaskCheckedChange
        )
        Text(
            text = task.title,
            textDecoration = if (task.isDone) TextDecoration.LineThrough else TextDecoration.None,
            modifier = Modifier.padding(horizontal = Padding.MEDIUM)
        )
    }
}

@Composable
fun TodoList(todoList: List<Task>) = TaskList(stringResource(R.string.todo_task), todoList)

@Composable
fun DoneList(doneList: List<Task>) = TaskList(stringResource(R.string.done_task), doneList)

@Composable
fun TaskList(categoryTitle: String, taskList: List<Task>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = categoryTitle,
            style = MaterialTheme.typography.headlineSmall
        )
        LazyColumn(modifier = Modifier.padding(vertical = Padding.MEDIUM)) {
            items(taskList) { task ->
                TaskItem(task) {}
            }
        }
    }
}

@Composable
fun AllTaskList(todoList: List<Task>, doneList: List<Task>) {
    Column(modifier = Modifier.fillMaxSize()) {
        when {
            todoList.isEmpty() && doneList.isEmpty() -> Surface {}
            todoList.isNotEmpty() && doneList.isEmpty() -> TodoList(todoList)
            todoList.isEmpty() && doneList.isNotEmpty() -> DoneList(doneList)
            else -> {
                TodoList(todoList)
                Spacer(modifier = Modifier.padding(vertical = Padding.LARGE))
                DoneList(doneList)
            }
        }
    }
}

@Composable
fun NoTask() {
    Text(
        text = stringResource(R.string.add_task_please),
        modifier = Modifier.fillMaxSize(),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodyMedium
    )
}

@Preview(heightDp = 500)
@Composable
fun TasksScreenPreview() {
    TasksScreen()
}

@Preview
@Composable
fun TaskComposablePreview() {
    ToDoComposeTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            TaskItem(
                Task("할일", "설명", true)
            ) {}
        }
    }
}

@Preview
@Composable
fun TaskListPreview() {
    ToDoComposeTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            TaskList(
                "할 일",
                listOf(
                    Task("할일1", "", false),
                    Task("할일2", "", false)
                )
            )
        }
    }
}

@Preview(heightDp = 500, showBackground = true)
@Composable
fun AllTaskListPreview() {
    val todoList = listOf(Task("할 일", "", false), Task("할 일2", "", false))
    val doneList = listOf(Task("끝냈음", "", true))
    val emptyList: List<Task> = emptyList()

    ToDoComposeTheme {
        AllTaskList(todoList, doneList)
    }
}

@Preview(heightDp = 500, showBackground = true)
@Composable
fun NoTaskPreview() {
    ToDoComposeTheme {
        NoTask()
    }
}
