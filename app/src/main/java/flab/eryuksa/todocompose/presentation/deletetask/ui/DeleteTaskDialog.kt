package flab.eryuksa.todocompose.presentation.deletetask.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import flab.eryuksa.todocompose.R
import flab.eryuksa.todocompose.presentation.components.CancelAndConfirmButtons
import flab.eryuksa.todocompose.presentation.theme.DELETE_TASK_DIALOG_WIDTH_FRACTION
import flab.eryuksa.todocompose.presentation.theme.DIALOG_ROUNDED_CORNER_SIZE_DP
import flab.eryuksa.todocompose.presentation.theme.Padding

@Composable
fun DeleteTaskDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    taskTitle: String
) {
    val dialogWidthDp =
        (LocalConfiguration.current.screenWidthDp * DELETE_TASK_DIALOG_WIDTH_FRACTION).toInt().dp

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier.width(dialogWidthDp),
            shape = RoundedCornerShape(size = DIALOG_ROUNDED_CORNER_SIZE_DP.dp),
            color = Color.White
        ) {
            Column(modifier = Modifier.padding(Padding.LARGE)) {
                DeleteTaskDialogContentsText(taskTitle = taskTitle)
                CancelAndConfirmButtons(
                    cancelText = stringResource(id = R.string.no),
                    confirmText = stringResource(id = R.string.yes),
                    onClickCancel = onDismiss,
                    onClickConfirm = onConfirm
                )
            }
        }
    }
}

@Composable
fun DeleteTaskDialogContentsText(taskTitle: String) {
    Text(
        text = stringResource(R.string.delete_task_dialog_contents_text, taskTitle),
        modifier = Modifier.padding(Padding.MEDIUM),
        style = MaterialTheme.typography.bodyMedium
    )
}

@Preview
@Composable
fun DeleteTaskScreenPreview() {
    Surface {
        DeleteTaskDialog(
            onDismiss = { },
            onConfirm = { },
            taskTitle = "할 일"
        )
    }
}