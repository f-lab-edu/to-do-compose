package flab.eryuksa.todocompose.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import flab.eryuksa.todocompose.data.database.TaskDao
import flab.eryuksa.todocompose.data.database.TaskDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private const val TASK_DATABASE_NAME = "task.db"

    @Provides
    @Singleton
    fun provideTaskDatabase(@ApplicationContext context: Context): TaskDatabase {
        return Room.databaseBuilder(
            context,
            TaskDatabase::class.java,
            TASK_DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providesTaskDao(database: TaskDatabase): TaskDao {
        return database.getDao()
    }
}