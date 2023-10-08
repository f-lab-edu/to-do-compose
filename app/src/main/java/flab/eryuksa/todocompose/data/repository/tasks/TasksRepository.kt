package flab.eryuksa.todocompose.data.repository.tasks

import flab.eryuksa.todocompose.data.model.Task
import kotlinx.coroutines.flow.Flow

interface TasksRepository {

    fun getTodoTasks(): Flow<List<Task>>
    fun getDoneTasks(): Flow<List<Task>>
    fun updateTaskToModifiedTask(modifiedTask: Task)
}