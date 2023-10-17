package flab.eryuksa.todocompose.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import flab.eryuksa.todocompose.data.repository.addtodo.AddTodoRepository
import flab.eryuksa.todocompose.data.repository.DefaultTaskRepository
import flab.eryuksa.todocompose.data.repository.deletetask.DeleteTaskRepository
import flab.eryuksa.todocompose.data.repository.taskdetails.TaskDetailsRepository
import flab.eryuksa.todocompose.data.repository.tasks.TasksRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindTasksRepository(tasksRepository: DefaultTaskRepository): TasksRepository

    @Binds
    @Singleton
    abstract fun bindAddTodoRepository(addTodoRepository: DefaultTaskRepository): AddTodoRepository

    @Binds
    @Singleton
    abstract fun bindDeleteTaskRepository(deleteTaskRepository: DefaultTaskRepository): DeleteTaskRepository

    @Binds
    @Singleton
    abstract fun bindTaskDetailsRepository(taskDetailsRepository: DefaultTaskRepository): TaskDetailsRepository
}