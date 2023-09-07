package flab.eryuksa.todocompose.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import flab.eryuksa.todocompose.ui.theme.Padding

@Composable
fun ResultButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier.padding(horizontal = Padding.MEDIUM)
    ) {
        Text(text)
    }
}