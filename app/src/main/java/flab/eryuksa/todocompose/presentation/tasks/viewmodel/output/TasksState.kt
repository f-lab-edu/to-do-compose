package flab.eryuksa.todocompose.presentation.tasks.viewmodel.output

import flab.eryuksa.todocompose.data.model.Task

sealed interface TasksState {

    object NoTask : TasksState
    data class OnlyTodoExist(val todoList: List<Task>) : TasksState
    data class OnlyDoneExist(val doneList: List<Task>) : TasksState
    data class TodoAndDoneExist(val todoList: List<Task>, val doneList: List<Task>) : TasksState

}
