package flab.eryuksa.todocompose.presentation.tasks.viewmodel.input

import flab.eryuksa.todocompose.data.model.Task

interface TasksInput {

    fun showAddTaskScreen()
    fun changeCheckedState(task: Task)
}