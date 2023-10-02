package flab.eryuksa.todocompose.ui.tasks.viewmodel.output

import flab.eryuksa.todocompose.ui.tasks.model.Task

sealed class TasksState {

    object NoTask : TasksState()
    data class OnlyTodoExist(val todoList: List<Task>) : TasksState()
    data class OnlyDoneExist(val doneList: List<Task>) : TasksState()
    data class TodoAndDoneExist(val todoList: List<Task>, val doneList: List<Task>) : TasksState()

}
