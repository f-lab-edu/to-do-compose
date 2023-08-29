package flab.eryuksa.todocompose.ui.tasks

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import flab.eryuksa.todocompose.ui.theme.ToDoComposeTheme

@Composable
fun TasksScreen() {
}

@Composable
fun TaskCompose(task: Task, onTaskCheckedChange: (Boolean) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            )
    ) {
        Checkbox(checked = task.isDone, onCheckedChange = onTaskCheckedChange)
        Text(text = task.title)
    }
}

@Composable
fun TaskList(categoryTitle: String, taskList: List<Task>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = categoryTitle, style = MaterialTheme.typography.headlineSmall)
        LazyColumn {
            items(taskList) { task ->
                TaskCompose(task) {}
            }
        }
    }
}

@Preview
@Composable
fun TasksScreenPreview() {
}

@Preview
@Composable
fun TaskComposePreview() {
    ToDoComposeTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            TaskCompose(
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
