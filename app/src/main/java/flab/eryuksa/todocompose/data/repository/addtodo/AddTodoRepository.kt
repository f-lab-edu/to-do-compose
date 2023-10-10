package flab.eryuksa.todocompose.data.repository.addtodo

interface AddTodoRepository {

    suspend fun addTodo(title: String, details: String)
}