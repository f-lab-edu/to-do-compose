package flab.eryuksa.todocompose.ui.tasks.viewmodel.input

import flab.eryuksa.todocompose.ui.tasks.model.Task

interface TasksInput {

    fun showAddTaskScreen()
    fun changeCheckState(task: Task)
}