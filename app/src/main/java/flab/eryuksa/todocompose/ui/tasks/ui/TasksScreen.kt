package flab.eryuksa.todocompose.ui.tasks.ui

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import flab.eryuksa.todocompose.R
import flab.eryuksa.todocompose.ui.tasks.viewmodel.input.AddTodoJob
import flab.eryuksa.todocompose.ui.addtodo.ui.AddTodoScreen
import flab.eryuksa.todocompose.ui.addtodo.viewmodel.AddTodoViewModel
import flab.eryuksa.todocompose.ui.addtodo.viewmodel.AddTodoViewModelFactory
import flab.eryuksa.todocompose.ui.tasks.model.Task
import flab.eryuksa.todocompose.ui.tasks.viewmodel.TasksViewModel
import flab.eryuksa.todocompose.ui.tasks.viewmodel.input.TasksInput
import flab.eryuksa.todocompose.ui.tasks.viewmodel.output.TasksEffect
import flab.eryuksa.todocompose.ui.tasks.viewmodel.output.TasksOutput
import flab.eryuksa.todocompose.ui.tasks.viewmodel.output.TasksState
import flab.eryuksa.todocompose.ui.theme.Padding
import flab.eryuksa.todocompose.ui.theme.ToDoComposeTheme
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TasksScreen(
    input: TasksInput,
    output: TasksOutput,
    addTodoViewModel: AddTodoViewModel
) {
    val uiState by output.uiState.collectAsState()
    var isAddTaskScreenShown by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        output.uiEffect.collectLatest { effect ->
            isAddTaskScreenShown = when (effect) {
                is TasksEffect.ShowAddTodoScreen -> true
                is TasksEffect.DismissAddTodoScreen -> false
            }
        }
    }

    Scaffold(
        containerColor = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .padding(Padding.MEDIUM),
        floatingActionButton = {
            FloatingActionButton(onClick = input::showAddTaskScreen) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = stringResource(R.string.add_task)
                )
            }
        }
    ) {
        if (isAddTaskScreenShown) {
            AddTodoScreen(input = addTodoViewModel, output = addTodoViewModel)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = Padding.LARGE)
        ) {
            when (val tasksState = uiState) {
                is TasksState.NoTask -> NoTask()
                is TasksState.OnlyTodoExist -> TodoList(
                    tasksState.todoList,
                    input::changeCheckState
                )

                is TasksState.OnlyDoneExist -> DoneList(
                    tasksState.doneList,
                    input::changeCheckState
                )

                is TasksState.TodoAndDoneExist -> {
                    TodoList(tasksState.todoList, input::changeCheckState)
                    Spacer(modifier = Modifier.padding(vertical = Padding.LARGE))
                    DoneList(tasksState.doneList, input::changeCheckState)
                }
            }
        }
    }
}

@Composable
fun TaskItem(task: Task, onTaskCheckedChange: (Task) -> Unit) {
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
            onCheckedChange = { onTaskCheckedChange(task) }
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
fun TodoList(todoList: List<Task>, onTaskCheckedChange: (Task) -> Unit) =
    TaskList(stringResource(R.string.todo_task), todoList, onTaskCheckedChange)

@Composable
fun DoneList(doneList: List<Task>, onTaskCheckedChange: (Task) -> Unit) =
    TaskList(stringResource(R.string.done_task), doneList, onTaskCheckedChange)

@Composable
fun TaskList(
    categoryTitle: String,
    taskList: List<Task>,
    onTaskCheckedChange: (Task) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = categoryTitle,
            style = MaterialTheme.typography.titleMedium
        )
        LazyColumn(modifier = Modifier.padding(vertical = Padding.MEDIUM)) {
            items(taskList) { task ->
                TaskItem(task, onTaskCheckedChange)
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
    val addTodoViewModel: AddTodoViewModel =
        viewModel(factory = AddTodoViewModelFactory(object : AddTodoJob {
            override fun addTodo(title: String, details: String) {}
            override fun cancelAddingTodo() {}
        }))
    TasksScreen(viewModel, viewModel, addTodoViewModel)
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
                onTaskCheckedChange = {}
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
                "할 일",
                listOf(
                    Task("할일1", "", false),
                    Task("할일2", "", false)
                ),
                {}
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
