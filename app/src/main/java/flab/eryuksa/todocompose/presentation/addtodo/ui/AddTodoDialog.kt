package flab.eryuksa.todocompose.presentation.addtodo.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import flab.eryuksa.todocompose.R
import flab.eryuksa.todocompose.presentation.addtodo.viewmodel.AddTodoViewModel
import flab.eryuksa.todocompose.presentation.addtodo.viewmodel.input.AddTodoInput
import flab.eryuksa.todocompose.presentation.addtodo.viewmodel.output.AddTodoOutput
import flab.eryuksa.todocompose.presentation.components.CancelAndConfirmButtons
import flab.eryuksa.todocompose.presentation.theme.ADD_TODO_DIALOG_HEIGHT_FRACTION
import flab.eryuksa.todocompose.presentation.theme.DIALOG_ROUNDED_CORNER_SIZE
import flab.eryuksa.todocompose.presentation.theme.Padding

@Composable
fun AddTodoDialog(input: AddTodoInput, output: AddTodoOutput) {
    val dialogHeightDp =
        (LocalConfiguration.current.screenHeightDp * ADD_TODO_DIALOG_HEIGHT_FRACTION).toInt().dp
    val uiState by output.uiState.collectAsState()

    Dialog(input::dismissScreen) {
        Surface(
            modifier = Modifier.height(dialogHeightDp),
            shape = RoundedCornerShape(size = DIALOG_ROUNDED_CORNER_SIZE.dp),
            color = Color.White
        ) {
            Column(modifier = Modifier.padding(Padding.LARGE)) {
                TitleTextField(uiState.title, input::updateTitle)
                DetailsTextField(
                    text = uiState.details,
                    onTextChange = input::updateDetails,
                    modifier = Modifier.weight(weight = 1f)
                )
                CancelAndConfirmButtons(
                    cancelText = stringResource(R.string.cancel),
                    confirmText = stringResource(R.string.add),
                    onClickCancel = input::dismissScreen,
                    onClickConfirm = input::addTodo
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TitleTextField(text: String, onTextChange: (String) -> Unit) {
    TextField(
        value = text,
        onValueChange = onTextChange,
        placeholder = { Text(stringResource(R.string.title)) },
        modifier = Modifier
            .fillMaxWidth(),
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(containerColor = Color.White),
        textStyle = MaterialTheme.typography.titleMedium
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsTextField(
    text: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = text,
        onValueChange = onTextChange,
        placeholder = { Text(stringResource(R.string.details)) },
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = Padding.SMALL),
        textStyle = MaterialTheme.typography.bodyMedium.copy(textAlign = TextAlign.Start),
        colors = TextFieldDefaults.textFieldColors(containerColor = Color.White),
    )
}

@Preview(heightDp = 720)
@Composable
fun AddTaskScreenPreview() {
    Surface {
        val viewModel: AddTodoViewModel = viewModel()
        AddTodoDialog(viewModel, viewModel)
    }
}
