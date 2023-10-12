package flab.eryuksa.todocompose.presentation.tasks.viewmodel.input

import flab.eryuksa.todocompose.data.entity.Task

interface TasksInput {

    fun showAddTaskScreen()
    fun changeCheckedState(task: Task)
    fun showDeleteTaskDialog(task: Task)
}