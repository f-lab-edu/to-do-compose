package flab.eryuksa.todocompose.presentation.details.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import flab.eryuksa.todocompose.R
import flab.eryuksa.todocompose.data.entity.Task
import flab.eryuksa.todocompose.presentation.theme.Padding
import flab.eryuksa.todocompose.presentation.theme.ToDoComposeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailsScreen(
    task: Task,
    onClickBackButton: () -> Unit,
    onClickDeleteButton: (task: Task) -> Unit,
    onClickCheckButton: (task: Task) -> Unit,
    onTitleChange: (title: String) -> Unit,
    onMemoChange: (memo: String) -> Unit
) {
    Scaffold(
        topBar = {
            TaskDetailsAppBar(
                task = task,
                onClickBackButton = onClickBackButton,
                onClickDeleteButton = onClickDeleteButton,
                onClickCheckButton = onClickCheckButton
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { paddingValue ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = paddingValue.calculateTopPadding().plus(Padding.LARGE),
                    start = Padding.LARGE,
                    end = Padding.LARGE,
                    bottom = Padding.LARGE
                )
        ) {
            TaskDetailsTitleTextField(
                title = task.title,
                onTitleChange = onTitleChange
            )
            Spacer(modifier = Modifier.height(Padding.LARGE))
            TaskDetailsMemoTextField(
                memo = task.memo,
                onMemoChange = onMemoChange
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailsAppBar(
    task: Task,
    onClickBackButton: () -> Unit,
    onClickDeleteButton: (task: Task) -> Unit,
    onClickCheckButton: (task: Task) -> Unit
) {
    TopAppBar(
        title = {},
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary
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
            IconButton(onClick = { onClickDeleteButton(task) }) {
                Icon(
                    imageVector = Icons.Rounded.Delete,
                    contentDescription = stringResource(R.string.back_button_description)
                )
            }
            IconButton(onClick = { onClickCheckButton(task) }) {
                Icon(
                    painter = if (task.isDone) {
                        painterResource(R.drawable.checked_circle_24dp)
                    } else {
                        painterResource(R.drawable.button_unchecked_24dp)
                    },
                    contentDescription = stringResource(R.string.back_button_description)
                )
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailsTitleTextField(title: String, onTitleChange: (title: String) -> Unit) {
    TextField(
        value = title,
        onValueChange = onTitleChange,
        placeholder = { Text(stringResource(R.string.title)) },
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        maxLines = 2,
        textStyle = MaterialTheme.typography.titleMedium
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColumnScope.TaskDetailsMemoTextField(memo: String, onMemoChange: (memo: String) -> Unit) {
    TextField(
        value = memo,
        onValueChange = onMemoChange,
        placeholder = { Text(stringResource(R.string.memo)) },
        modifier = Modifier
            .weight(1f)
            .fillMaxWidth(),
        textStyle = MaterialTheme.typography.bodyMedium.copy(textAlign = TextAlign.Start)
    )
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
    ToDoComposeTheme {
        TaskDetailsScreen(
            task = Task("할 일 제목", "할 일 메모", isDone = true),
            onClickBackButton = {},
            onClickDeleteButton = {},
            onClickCheckButton = {},
            onTitleChange = {},
            onMemoChange = {}
        )
    }
}