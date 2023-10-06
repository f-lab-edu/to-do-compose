package flab.eryuksa.todocompose.ui.addtodo.viewmodel.output

import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface AddTodoOutput {

    val uiState: StateFlow<AddTodoState>
    val uiEffect: SharedFlow<AddTodoEffect>
}