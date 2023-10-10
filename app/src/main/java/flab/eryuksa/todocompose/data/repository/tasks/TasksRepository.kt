package flab.eryuksa.todocompose.data.repository.tasks

import flab.eryuksa.todocompose.data.entity.Task
import kotlinx.coroutines.flow.Flow

interface TasksRepository {

    fun getTodoTasks(): Flow<List<Task>>
    fun getDoneTasks(): Flow<List<Task>>
    suspend fun updateTaskToModifiedTask(modifiedTask: Task)
}