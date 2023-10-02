package flab.eryuksa.todocompose.ui.tasks.viewmodel.output

sealed class TasksEffect {

    object ShowAddTodoScreen : TasksEffect()
    object DismissAddTodoScreen : TasksEffect()
}
