package flab.eryuksa.todocompose.ui.addtodo.viewmodel.input

interface AddTodoInput {

    fun updateTitle(newTitle: String)
    fun updateDetails(newDetails: String)
    fun addTodo()
    fun cancelAddingTodo()
}