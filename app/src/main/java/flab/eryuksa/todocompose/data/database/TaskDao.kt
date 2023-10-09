package flab.eryuksa.todocompose.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import flab.eryuksa.todocompose.data.entity.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Query("SELECT * FROM task")
    fun getAllTask(): Flow<List<Task>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertTask(newTask: Task)

    @Update
    suspend fun updateTask(modifiedTask: Task)

    @Delete
    suspend fun deleteTask(task: Task)
}