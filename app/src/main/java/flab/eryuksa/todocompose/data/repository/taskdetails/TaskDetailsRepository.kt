package flab.eryuksa.todocompose.data.repository.taskdetails

import flab.eryuksa.todocompose.data.entity.Task

interface TaskDetailsRepository {

    suspend fun updateTaskToModifiedTask(modifiedTask: Task)
}