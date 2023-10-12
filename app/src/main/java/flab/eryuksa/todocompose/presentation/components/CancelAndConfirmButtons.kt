package flab.eryuksa.todocompose.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import flab.eryuksa.todocompose.presentation.theme.Padding
@Composable
fun CancelAndConfirmButtons(
    cancelText: String,
    confirmText: String,
    onClickCancel: () -> Unit,
    onClickConfirm: () -> Unit
) {
    Row(modifier = Modifier.padding(top = Padding.MEDIUM)) {
        CancelButton(cancelText, onClickCancel)
        ConfirmButton(confirmText, onClickConfirm)
    }
}

@Composable
fun RowScope.CancelButton(cancelText: String, onClickCancel: () -> Unit) {
    ResultButton(
        text = cancelText,
        onClick = onClickCancel
    )
}

@Composable
fun RowScope.ConfirmButton(confirmText: String, onClickAdd: () -> Unit) {
    ResultButton(
        text = confirmText,
        onClick = onClickAdd,
    )
}
@Composable
fun RowScope.ResultButton(
    text: String,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(horizontal = Padding.MEDIUM)
            .weight(1f)
    ) {
        Text(text)
    }
}