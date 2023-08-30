package flab.eryuksa.todocompose.ui.tasks

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import flab.eryuksa.todocompose.R
import flab.eryuksa.todocompose.ui.theme.ToDoComposeTheme

@Composable
fun TasksScreen() {
}

@Composable
fun TaskComposable(task: Task, onTaskCheckedChange: (Boolean) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = dimensionResource(id = R.dimen.margin_16dp),
                vertical = dimensionResource(id = R.dimen.margin_8dp)
            )
    ) {
        Checkbox(checked = task.isDone, onCheckedChange = onTaskCheckedChange)
        Spacer(Modifier.padding(horizontal = dimensionResource(id = R.dimen.margin_8dp)))
        Text(text = task.title, textDecoration = if (task.isDone) TextDecoration.LineThrough else TextDecoration.None)
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
                Spacer(modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.margin_16dp)))
                DoneList(doneList)
            }
        }
    }
}

@Composable
fun TodoList(todoList: List<Task>) = TaskList(stringResource(R.string.todo_task), todoList)

@Composable
fun DoneList(doneList: List<Task>) = TaskList(stringResource(R.string.done_task), doneList)

@Composable
fun TaskList(categoryTitle: String, taskList: List<Task>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = categoryTitle, style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.padding(vertical = dimensionResource(id = R.dimen.margin_8dp)))
        LazyColumn {
            items(taskList) { task ->
                TaskComposable(task) {}
            }
        }
    }
}

@Preview
@Composable
fun TasksScreenPreview() {
    ToDoComposeTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            TasksScreen()
        }
    }
}

@Preview
@Composable
fun TaskComposablePreview() {
    ToDoComposeTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            TaskComposable(
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

@Preview
@Composable
fun AllTaskListPreview() {
    val todoList = listOf(Task("할 일", "", false), Task("할 일2", "", false))
    val doneList = listOf(Task("끝냈음", "", true))
    val emptyList: List<Task> = emptyList()

    ToDoComposeTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            AllTaskList(todoList, doneList)
        }
    }
}
