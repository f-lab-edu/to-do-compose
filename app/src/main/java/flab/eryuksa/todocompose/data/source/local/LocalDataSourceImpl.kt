package flab.eryuksa.todocompose.data.source.local

import flab.eryuksa.todocompose.data.database.TaskDao
import flab.eryuksa.todocompose.data.entity.Task
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val taskDao: TaskDao
) : LocalDataSource {

    override fun getAllTaskStream(): Flow<List<Task>> {
        return taskDao.getAllTask()
    }

    override suspend fun addTask(newTask: Task) {
        taskDao.insertTask(newTask)
    }

    override suspend fun updateTask(modifiedTask: Task) {
        taskDao.updateTask(modifiedTask)
    }
}