package flab.eryuksa.todocompose.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import flab.eryuksa.todocompose.data.repository.addtodo.AddTodoRepository
import flab.eryuksa.todocompose.data.repository.DefaultTasksRepository
import flab.eryuksa.todocompose.data.repository.tasks.TasksRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindTasksRepository(tasksRepository: DefaultTasksRepository): TasksRepository

    @Binds
    @Singleton
    abstract fun bindAddTodoRepository(addTodoRepository: DefaultTasksRepository): AddTodoRepository

}