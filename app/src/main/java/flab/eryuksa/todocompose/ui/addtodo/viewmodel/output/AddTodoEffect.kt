package flab.eryuksa.todocompose.ui.addtodo.viewmodel.output

sealed interface AddTodoEffect {

    object DismissAddTodoScreen : AddTodoEffect
}