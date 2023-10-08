package flab.eryuksa.todocompose.presentation.addtodo.viewmodel.output

sealed interface AddTodoEffect {

    object DismissAddTodoScreen : AddTodoEffect
}