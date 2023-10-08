package flab.eryuksa.todocompose.data.repository.addtodo

interface AddTodoRepository {

    fun addTodo(title: String, details: String)
}