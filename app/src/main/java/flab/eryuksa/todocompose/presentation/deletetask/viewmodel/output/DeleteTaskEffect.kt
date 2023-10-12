package flab.eryuksa.todocompose.presentation.deletetask.viewmodel.output

sealed interface DeleteTaskEffect {

    object DismissDeleteTaskScreen : DeleteTaskEffect
}