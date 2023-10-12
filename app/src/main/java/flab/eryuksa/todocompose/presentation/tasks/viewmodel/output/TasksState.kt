package flab.eryuksa.todocompose.presentation.tasks.viewmodel.output

import flab.eryuksa.todocompose.data.entity.Task

data class TasksState(
    val todoList: List<Task>,
    val doneList: List<Task>
) {
    companion object {
        val INITIAL = TasksState(emptyList(), emptyList())
    }
}
