package flab.eryuksa.todocompose.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import flab.eryuksa.todocompose.presentation.theme.Padding

@Composable
fun ResultButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier.padding(horizontal = Padding.MEDIUM).fillMaxWidth()
    ) {
        Text(text)
    }
}