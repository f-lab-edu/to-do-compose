package flab.eryuksa.todocompose.presentation.addtodo.viewmodel.input

interface AddTodoInput {

    fun updateTitle(newTitle: String)
    fun updateDetails(newDetails: String)
    fun addTodo()
    fun dismissScreen()
}