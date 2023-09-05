package flab.eryuksa.todocompose.ui.addtask

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
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
import flab.eryuksa.todocompose.ui.theme.Padding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskDialog(onClickAdd: () -> Unit, onClickCancel: () -> Unit) {
    var title by remember { mutableStateOf("") }
    var details by remember { mutableStateOf("") }

    Dialog(onDismissRequest = { }) {
        Surface(
            modifier = Modifier
                .height((LocalConfiguration.current.screenHeightDp * 0.5).toInt().dp)
                .padding(Padding.MEDIUM),
            shape = RoundedCornerShape(size = Padding.MEDIUM),
            color = Color.White
        ) {
            Column() {
                TextField(
                    value = title,
                    onValueChange = { title = it },
                    placeholder = { Text(stringResource(R.string.title)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = Padding.SMALL),
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(containerColor = Color.White)
                )
                TextField(
                    value = details,
                    onValueChange = { details = it },
                    placeholder = { Text(stringResource(R.string.details)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(weight = 1f)
                        .padding(start = Padding.SMALL, end = Padding.SMALL, bottom = Padding.SMALL),
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Start),
                    colors = TextFieldDefaults.textFieldColors(containerColor = Color.White)
                )
                Row {
                    Button(
                        onClick = onClickCancel,
                        modifier = Modifier
                            .weight(weight = 1f)
                            .padding(horizontal = Padding.MEDIUM)
                    ) {
                        Text("취소")
                    }
                    Button(
                        onClick = onClickAdd,
                        modifier = Modifier
                            .weight(weight = 1f)
                            .padding(horizontal = Padding.LARGE)
                    ) {
                        Text("추가")
                    }
                }
            }
        }
    }
}

/*
@Composable
fun TitleTextField() {
    TextField(
        value = title,
        onValueChange = { title = it },
        placeholder = { Text(stringResource(R.string.title)) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Padding.SMALL),
        singleLine = true
    )
}*/

@Preview(heightDp = 360)
@Composable
fun AddTaskDialogPreview() {
    Surface {
        AddTaskDialog({}, {})
    }
}
