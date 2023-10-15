package flab.eryuksa.todocompose.presentation.details.viewmodel.output

import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface TaskDetailsOutput {

    val uiState: StateFlow<TaskDetailsState>
    val uiEffect: SharedFlow<TaskDetailsEffect>
}