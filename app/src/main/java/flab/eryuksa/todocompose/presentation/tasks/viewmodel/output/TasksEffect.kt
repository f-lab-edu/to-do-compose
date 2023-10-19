package flab.eryuksa.todocompose.presentation.tasks.viewmodel.output

import flab.eryuksa.todocompose.data.entity.Task

sealed interface TasksEffect {

    object ShowAddTodoScreen : TasksEffect
    data class ShowDeleteTaskDialog(val taskToBeDeleted: Task) : TasksEffect
    data class ShowTaskDetailsScreen(val task: Task) : TasksEffect
}
