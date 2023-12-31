package flab.eryuksa.todocompose.presentation.tasks.viewmodel.output

import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface TasksOutput {

    val uiState: StateFlow<TasksState>
    val uiEffect: SharedFlow<TasksEffect>
}