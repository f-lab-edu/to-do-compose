package flab.eryuksa.todocompose.ui.addtask

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import flab.eryuksa.todocompose.R
import flab.eryuksa.todocompose.ui.theme.Padding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskDialog() {
    var title by remember { mutableStateOf("") }
    var details by remember { mutableStateOf("") }

    Dialog(onDismissRequest = { }) {
        Column {
            TextField(
                value = title,
                onValueChange = { title = it },
                placeholder = { Text(stringResource(R.string.title)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Padding.SMALL),
                singleLine = true
            )
            Spacer(
                modifier = Modifier.border(
                    width = 1.dp,
                    color = Color.DarkGray
                )
            )
            TextField(
                value = details,
                onValueChange = { details = it },
                placeholder = { Text(stringResource(R.string.details)) },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = Padding.SMALL, end = Padding.SMALL, bottom = Padding.SMALL)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(
                            topStart = Padding.NONE,
                            topEnd = Padding.NONE,
                            bottomStart = Padding.SMALL,
                            bottomEnd = Padding.SMALL
                        )
                    ),
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Start)
            )
        }
    }
}

@Preview(heightDp = 180)
@Composable
fun AddTaskDialogPreview() {
    Surface {
        AddTaskDialog()
    }
}
