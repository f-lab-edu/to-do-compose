package flab.eryuksa.todocompose.ui.tasks

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import flab.eryuksa.todocompose.R
import flab.eryuksa.todocompose.ui.addtodo.AddTodoScreen
import flab.eryuksa.todocompose.ui.addtodo.AddTodoViewModelFactory
import flab.eryuksa.todocompose.ui.theme.Padding
import flab.eryuksa.todocompose.ui.theme.ToDoComposeTheme

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TasksScreen(viewModel: TasksViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        containerColor = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .padding(Padding.MEDIUM),
        floatingActionButton = {
            FloatingActionButton(onClick = viewModel::showAddTaskScreen) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = stringResource(R.string.add_task)
                )
            }
        }
    ) {
        if (uiState.isAddTaskScreenShown) {
            AddTodoScreen(
                viewModel(factory = AddTodoViewModelFactory(tasksViewModel = viewModel))
            )
        }

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(all = Padding.LARGE)) {
            when(val taskListState = uiState.taskListState) {
                is TaskListState.NoTask -> NoTask()
                is TaskListState.OnlyTodoExist -> TodoList(taskListState.todoList)
                is TaskListState.OnlyDoneExist -> DoneList(taskListState.doneList)
                is TaskListState.TodoAndDoneExist -> {
                    TodoList(taskListState.todoList)
                    Spacer(modifier = Modifier.padding(vertical = Padding.LARGE))
                    DoneList(taskListState.doneList)
                }
            }
        }
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
            style = MaterialTheme.typography.bodyMedium,
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
            style = MaterialTheme.typography.titleMedium
        )
        LazyColumn(modifier = Modifier.padding(vertical = Padding.MEDIUM)) {
            items(taskList) { task ->
                TaskItem(task) {}
            }
        }
    }
}

@Composable
fun NoTask() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.add_task_please),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview(heightDp = 500, showBackground = true)
@Composable
fun TasksScreenPreview() {
    TasksScreen(viewModel())
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
fun NoTaskPreview() {
    ToDoComposeTheme {
        NoTask()
    }
}
