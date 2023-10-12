package flab.eryuksa.todocompose.presentation.tasks.viewmodel.output

import flab.eryuksa.todocompose.data.entity.Task

sealed interface TasksEffect {

    object ShowAddTodoScreen : TasksEffect
    data class ShowDeleteTaskScreen(val taskToBeDeleted: Task) : TasksEffect
}
