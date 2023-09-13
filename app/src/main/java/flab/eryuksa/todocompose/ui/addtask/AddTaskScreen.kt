package flab.eryuksa.todocompose.ui.addtask

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import flab.eryuksa.todocompose.R
import flab.eryuksa.todocompose.ui.components.ResultButton
import flab.eryuksa.todocompose.ui.theme.DIALOG_HEIGHT_FRACTION
import flab.eryuksa.todocompose.ui.theme.Padding

@Composable
fun AddTaskScreen(
    onDismissRequest: () -> Unit,
    onClickAdd: () -> Unit,
    onClickCancel: () -> Unit
) {
    val dialogHeightDp = (LocalConfiguration.current.screenHeightDp * DIALOG_HEIGHT_FRACTION).toInt().dp
    var title by remember { mutableStateOf("") }
    var details by remember { mutableStateOf("") }
    val onTitleChanged = { newTitle: String -> title = newTitle }
    val onDetailsChanged = { newDetails: String -> details = newDetails }

    Dialog(onDismissRequest) {
        Surface(
            modifier = Modifier
                .height(dialogHeightDp)
                .padding(Padding.LARGE),
            shape = RoundedCornerShape(size = Padding.MEDIUM),
            color = Color.White
        ) {
            Column(modifier = Modifier.padding(Padding.LARGE)) {
                TitleTextField(title, onTitleChanged)
                DetailsTextField(
                    details,
                    onDetailsChanged,
                    Modifier.weight(weight = 1f)
                )
                CancelAndAddButtons(onClickAdd, onClickCancel)
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
        colors = TextFieldDefaults.textFieldColors(containerColor = Color.White)
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
        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Start),
        colors = TextFieldDefaults.textFieldColors(containerColor = Color.White)
    )
}

@Composable
fun CancelAndAddButtons(onClickCancel: () -> Unit, onClickAdd: () -> Unit) {
    Row(modifier = Modifier.padding(top = Padding.MEDIUM)) {
        CancelButton(onClickCancel)
        AddButton(onClickAdd)
    }
}

@Composable
fun RowScope.CancelButton(onClickCancel: () -> Unit) {
    Row(modifier = Modifier.weight(1f)) {
        ResultButton(
            text = stringResource(R.string.cancel),
            onClick = onClickCancel,
        )
    }
}

@Composable
fun RowScope.AddButton(onClickAdd: () -> Unit) {
    Row(modifier = Modifier.weight(1f)) {
        ResultButton(
            text = stringResource(R.string.add),
            onClick = onClickAdd,
        )
    }
}


@Preview(heightDp = 720)
@Composable
fun AddTaskDialogPreview() {
    Surface {
        AddTaskScreen({}, {}, {})
    }
}
