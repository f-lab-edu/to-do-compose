package flab.eryuksa.todocompose.data.source.local

import flab.eryuksa.todocompose.data.entity.Task
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    fun getAllTaskStream(): Flow<List<Task>>
    suspend fun addTask(newTask: Task)
    suspend fun updateTask(modifiedTask: Task)
}