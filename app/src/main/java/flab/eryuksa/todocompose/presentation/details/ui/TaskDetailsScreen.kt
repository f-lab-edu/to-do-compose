package flab.eryuksa.todocompose.presentation.details.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import flab.eryuksa.todocompose.R
import flab.eryuksa.todocompose.data.entity.Task
import flab.eryuksa.todocompose.presentation.details.viewmodel.TaskDetailsViewModel
import flab.eryuksa.todocompose.presentation.details.viewmodel.input.TaskDetailsInput
import flab.eryuksa.todocompose.presentation.details.viewmodel.output.TaskDetailsState
import flab.eryuksa.todocompose.presentation.theme.Padding
import flab.eryuksa.todocompose.presentation.theme.TASK_DETAILS_MEMO_MIN_HEIGHT_DP
import flab.eryuksa.todocompose.presentation.theme.TASK_DETAILS_ROUNDED_CORNER_SIZE_DP
import flab.eryuksa.todocompose.presentation.theme.ToDoComposeTheme
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailsScreen(
    taskDetailsState: StateFlow<TaskDetailsState>,
    input: TaskDetailsInput
) {
    val uiState by taskDetailsState.collectAsState()

    Scaffold(
        topBar = {
            TaskDetailsAppBar(
                task = uiState.task,
                onClickBackButton = input::updateTaskAndGoToBackScreen,
                onClickDeleteButton = input::showDeleteTaskDialog,
                onClickCheckButton = input::changeTaskDoneState
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { paddingValue ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = paddingValue
                        .calculateTopPadding()
                        .plus(Padding.XLARGE),
                    start = Padding.LARGE,
                    end = Padding.LARGE,
                    bottom = Padding.LARGE
                )
        ) {
            TaskDetailsTitleTextField(
                title = uiState.task.title,
                onTitleChange = input::updateTitle
            )
            Spacer(modifier = Modifier.height(Padding.LARGE))
            TaskDetailsMemoTextField(
                memo = uiState.task.memo,
                onMemoChange = input::updateMemo
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailsAppBar(
    task: Task,
    onClickBackButton: () -> Unit,
    onClickDeleteButton: () -> Unit,
    onClickCheckButton: () -> Unit
) {
    TopAppBar(
        title = { TaskDetailsAppBarTitle(isTaskDone = task.isDone) },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        navigationIcon = {
            IconButton(onClick = onClickBackButton) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = stringResource(R.string.back_button_description)
                )
            }
        },
        actions = {
            IconButton(onClick = onClickDeleteButton) {
                Icon(
                    imageVector = Icons.Rounded.Delete,
                    contentDescription = stringResource(R.string.delete_task_button_description)
                )
            }
            IconButton(onClick = onClickCheckButton) {
                Icon(
                    painter = if (task.isDone) {
                        painterResource(id = R.drawable.check_box_24dp)
                    } else {
                        painterResource(id = R.drawable.check_box_outline_blank_24dp)
                    },
                    contentDescription = if (task.isDone) {
                        stringResource(id = R.string.make_task_todo)
                    } else {
                        stringResource(id = R.string.make_task_done)
                    }
                )
            }
        },
    )
}

@Composable
fun TaskDetailsAppBarTitle(isTaskDone: Boolean) {
    Text(
        text = if (isTaskDone) {
            stringResource(R.string.done_task)
        } else {
            stringResource(R.string.todo_task)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailsTitleTextField(title: String, onTitleChange: (newTitle: String) -> Unit) {
    TextField(
        value = title,
        onValueChange = onTitleChange,
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        placeholder = { Text(stringResource(R.string.title)) },
        maxLines = 2,
        textStyle = MaterialTheme.typography.titleMedium,
        shape = RoundedCornerShape(size = TASK_DETAILS_ROUNDED_CORNER_SIZE_DP.dp),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailsMemoTextField(memo: String, onMemoChange: (memo: String) -> Unit) {
    Column(modifier = Modifier.padding(bottom = Padding.LARGE)) {
        Text(
            text = stringResource(R.string.memo),
            modifier = Modifier.padding(
                top = Padding.MEDIUM,
                bottom = Padding.LARGE
            ),
            style = MaterialTheme.typography.bodyLarge
        )
        TextField(
            value = memo,
            onValueChange = onMemoChange,
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = TASK_DETAILS_MEMO_MIN_HEIGHT_DP.dp),
            textStyle = MaterialTheme.typography.bodyMedium.copy(textAlign = TextAlign.Start),
            shape = RoundedCornerShape(size = TASK_DETAILS_ROUNDED_CORNER_SIZE_DP.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
    }
}

@Preview
@Composable
fun TasksDetailsAppBarPreview() {
    ToDoComposeTheme {
        TaskDetailsAppBar(
            task = Task("할 일 제목", "할 일 메모", isDone = true),
            onClickBackButton = { },
            onClickDeleteButton = {},
            onClickCheckButton = {}
        )
    }
}

@Preview(heightDp = 480)
@Composable
fun TasksDetailsScreenPreview() {
    val viewModel: TaskDetailsViewModel = viewModel()
    ToDoComposeTheme {
        TaskDetailsScreen(
            taskDetailsState = viewModel.uiState,
            input = viewModel
        )
    }
}