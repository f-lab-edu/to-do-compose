package flab.eryuksa.todocompose.presentation.tasks.ui

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
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import flab.eryuksa.todocompose.data.entity.Task
import flab.eryuksa.todocompose.presentation.tasks.viewmodel.TasksViewModel
import flab.eryuksa.todocompose.presentation.tasks.viewmodel.input.TasksInput
import flab.eryuksa.todocompose.presentation.tasks.viewmodel.output.TasksOutput
import flab.eryuksa.todocompose.presentation.theme.Padding
import flab.eryuksa.todocompose.presentation.theme.ToDoComposeTheme

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TasksScreen(input: TasksInput, output: TasksOutput) {
    val uiState by output.uiState.collectAsState()

    Scaffold(
        containerColor = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .padding(Padding.MEDIUM),
        floatingActionButton = {
            FloatingActionButton(onClick = input::showAddTaskScreen) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = stringResource(R.string.add_task_button_description)
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = Padding.LARGE)
        ) {
            TodoList(
                todoList = uiState.todoList,
                onTaskCheckedChange = input::changeCheckedState,
                onClickDeleteTask = input::showDeleteTaskDialog
            )
            DoneList(
                doneList = uiState.doneList,
                onTaskCheckedChange = input::changeCheckedState,
                onClickDeleteTask = input::showDeleteTaskDialog
            )
        }
    }
}

@Composable
fun TaskItem(
    task: Task,
    onTaskCheckedChange: (Task) -> Unit,
    onClickDeleteTask: (Task) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = Padding.MEDIUM,
                vertical = Padding.MEDIUM
            )
    ) {
        Checkbox(
            checked = task.isDone,
            onCheckedChange = { onTaskCheckedChange(task) }
        )
        Text(
            text = task.title,
            style = MaterialTheme.typography.bodyMedium,
            textDecoration = if (task.isDone) TextDecoration.LineThrough else TextDecoration.None,
            modifier = Modifier
                .padding(horizontal = Padding.MEDIUM)
                .weight(weight = 1f)
        )
        DeleteTaskButton(onClick = { onClickDeleteTask(task) })
    }
}

@Composable
fun DeleteTaskButton(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = Icons.Rounded.Delete,
            contentDescription = stringResource(id = R.string.delete_task_button_description)
        )
    }
}

@Composable
fun TodoList(
    todoList: List<Task>,
    onTaskCheckedChange: (Task) -> Unit,
    onClickDeleteTask: (Task) -> Unit
) {
    if (todoList.isNotEmpty()) {
        TaskList(
            categoryTitle = stringResource(R.string.todo_task),
            taskList = todoList,
            onTaskCheckedChange = onTaskCheckedChange,
            onClickDeleteTask = onClickDeleteTask
        )
        Spacer(modifier = Modifier.padding(vertical = Padding.LARGE))
    }
}

@Composable
fun DoneList(
    doneList: List<Task>,
    onTaskCheckedChange: (Task) -> Unit,
    onClickDeleteTask: (Task) -> Unit
) {
    if (doneList.isNotEmpty()) {
        TaskList(
            categoryTitle = stringResource(R.string.done_task),
            taskList = doneList,
            onTaskCheckedChange = onTaskCheckedChange,
            onClickDeleteTask = onClickDeleteTask
        )
    }
}

@Composable
fun TaskList(
    categoryTitle: String,
    taskList: List<Task>,
    onTaskCheckedChange: (Task) -> Unit,
    onClickDeleteTask: (Task) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = categoryTitle,
            style = MaterialTheme.typography.titleMedium
        )
        LazyColumn {
            items(taskList) { task ->
                TaskItem(
                    task = task,
                    onTaskCheckedChange = onTaskCheckedChange,
                    onClickDeleteTask = onClickDeleteTask
                )
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
    val viewModel: TasksViewModel = viewModel()
    TasksScreen(viewModel, viewModel)
}

@Preview
@Composable
fun TaskComposablePreview() {
    ToDoComposeTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            TaskItem(
                task = Task("할일", "설명", true),
                onTaskCheckedChange = {},
                onClickDeleteTask = {}
            )
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
                categoryTitle = "할 일",
                taskList = listOf(
                    Task("할일1", "", false),
                    Task("할일2", "", false)
                ),
                onTaskCheckedChange = {},
                onClickDeleteTask = {}
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
