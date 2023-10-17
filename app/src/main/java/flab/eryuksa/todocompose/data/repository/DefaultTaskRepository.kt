package flab.eryuksa.todocompose.data.repository

import flab.eryuksa.todocompose.data.entity.Task
import flab.eryuksa.todocompose.data.repository.addtodo.AddTodoRepository
import flab.eryuksa.todocompose.data.repository.deletetask.DeleteTaskRepository
import flab.eryuksa.todocompose.data.repository.taskdetails.TaskDetailsRepository
import flab.eryuksa.todocompose.data.repository.tasks.TasksRepository
import flab.eryuksa.todocompose.data.source.local.LocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DefaultTaskRepository @Inject constructor(
    private val localDataSource: LocalDataSource
) : TasksRepository, AddTodoRepository, DeleteTaskRepository, TaskDetailsRepository {

    private val taskList: Flow<List<Task>> = localDataSource.getAllTaskStream()

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

    override suspend fun updateTaskToModifiedTask(modifiedTask: Task) {
        localDataSource.updateTask(modifiedTask)
    }

    override suspend fun addTodo(title: String, details: String) {
        localDataSource.addTask(
            Task(
                title = title,
                memo = details,
                isDone = false
            )
        )
    }

    override suspend fun deleteTask(task: Task) {
        localDataSource.deleteTask(task)
    }
}