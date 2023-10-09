package flab.eryuksa.todocompose.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import flab.eryuksa.todocompose.data.entity.Task

@Database(entities = [Task::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun getDao(): TaskDao
}