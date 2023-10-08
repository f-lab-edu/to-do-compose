package flab.eryuksa.todocompose.data.repository

import flab.eryuksa.todocompose.data.model.Task
import flab.eryuksa.todocompose.data.repository.addtodo.AddTodoRepository
import flab.eryuksa.todocompose.data.repository.tasks.TasksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultTasksRepository @Inject constructor(): TasksRepository, AddTodoRepository {

    private val taskList = MutableStateFlow<List<Task>>(emptyList())

    private val todoList: Flow<List<Task>> = taskList.map { taskList ->
        taskList.filterNot { it.isDone }
    }
    private val doneList: Flow<List<Task>> = taskList.map { taskList ->
        taskList.filter { it.isDone }
    }

    override fun getTodoTasks(): Flow<List<Task>> {
        return todoList
    }

    override fun getDoneTasks(): Flow<List<Task>> {
        return doneList
    }

    override fun updateTaskToModifiedTask(modifiedTask: Task) {
        taskList.value = taskList.value.map { originalTask ->
            if (originalTask.id == modifiedTask.id) modifiedTask else originalTask
        }
    }

    override fun addTodo(title: String, details: String) {
        taskList.value += Task(title, details, isDone = false)
    }
}