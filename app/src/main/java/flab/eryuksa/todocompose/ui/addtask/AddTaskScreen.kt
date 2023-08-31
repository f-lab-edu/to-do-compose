package flab.eryuksa.todocompose.ui.addtask

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun AddTaskDialog() {
    /*var text by remember { mutableStateOf("") }

    Dialog(onDismissRequest = { }, DialogProperties()) {
        TextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = Text("제목"),
            singleLine = true,
        )
    }*/
}

@Preview
@Composable
fun AddTaskDialogPreview() {
    AddTaskDialog()
}
