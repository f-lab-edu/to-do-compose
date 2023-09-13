package flab.eryuksa.todocompose.ui.addtodo

interface AddTodoJob {

    fun addTodo(title: String, details: String)
    fun cancelAddingTodo()
}