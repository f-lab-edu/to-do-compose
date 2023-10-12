package flab.eryuksa.todocompose.presentation.deletetask.viewmodel.output

import flab.eryuksa.todocompose.data.entity.Task
import kotlinx.coroutines.flow.SharedFlow

interface DeleteTaskOutput {

    val task: Task
    val uiEffect: SharedFlow<DeleteTaskEffect>
}