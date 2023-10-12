package flab.eryuksa.todocompose.data.repository.deletetask

import flab.eryuksa.todocompose.data.entity.Task

interface DeleteTaskRepository {

    suspend fun deleteTask(task: Task)
}