package flab.eryuksa.todocompose.ui.tasks

sealed class TaskListState {

    object NoTask : TaskListState()
    data class OnlyTodoExist(val todoList: List<Task>) : TaskListState()
    data class OnlyDoneExist(val doneList: List<Task>) : TaskListState()
    data class TodoAndDoneExist(val todoList: List<Task>, val doneList: List<Task>) : TaskListState()

}
