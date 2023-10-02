package flab.eryuksa.todocompose.ui.tasks

import androidx.lifecycle.ViewModel
import flab.eryuksa.todocompose.ui.addtodo.AddTodoJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class TasksViewModel : ViewModel(), AddTodoJob {

    private val _uiState = MutableStateFlow(
        TasksUiState(isAddTaskScreenShown = false, TaskListState.NoTask)
    )
    val uiState: StateFlow<TasksUiState>
        get() = _uiState

    private val todoList = mutableListOf<Task>()
    private val doneList = mutableListOf<Task>()

    fun showAddTaskScreen() {
        _uiState.update { it.copy(isAddTaskScreenShown = true) }
    }

    private fun dismissAddTaskScreen() {
        _uiState.update { it.copy(isAddTaskScreenShown = false) }
    }

    override fun addTodo(title: String, details: String) {
        addTask(Task(title, details, isDone = false))
        updateTaskListState()
        dismissAddTaskScreen()
    }

    override fun cancelAddingTodo() {
        dismissAddTaskScreen()
    }

    fun changeCheckState(task: Task) {
        removeTask(task)
        addTask(task.copy(isDone = task.isDone.not()))
        updateTaskListState()
    }

    private fun createUpdatedTaskListState(): TaskListState {
        return when {
            todoList.isEmpty() && doneList.isEmpty() -> TaskListState.NoTask
            todoList.isNotEmpty() && doneList.isNotEmpty() -> TaskListState.TodoAndDoneExist(todoList, doneList)
            todoList.isNotEmpty() -> TaskListState.OnlyTodoExist(todoList)
            else -> TaskListState.OnlyDoneExist(doneList)
        }
    }

    private fun updateTaskListState() {
        _uiState.update { it.copy(taskListState = createUpdatedTaskListState()) }
    }

    private fun addTask(task: Task) {
        if (task.isDone) {
            doneList.add(task)
        } else {
            todoList.add(task)
        }
    }

    private fun removeTask(task: Task) {
        if (task.isDone) {
            doneList.remove(task)
        } else {
            todoList.remove(task)
        }
    }
}