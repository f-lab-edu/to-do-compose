package flab.eryuksa.todocompose.presentation.details.viewmodel.output

import flab.eryuksa.todocompose.data.entity.Task

sealed interface TaskDetailsEffect {

    object GoBackScreen : TaskDetailsEffect
    data class ShowDeleteTaskDialog(val taskToBeDeleted: Task) : TaskDetailsEffect
}