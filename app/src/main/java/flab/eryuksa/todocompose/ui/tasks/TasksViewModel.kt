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
        todoList.add(Task(title, details, isDone = false))
        _uiState.update { it.copy(taskListState = createNewTaskListStateFromCurrentOne()) }
        dismissAddTaskScreen()
    }

    override fun cancelAddingTodo() {
        dismissAddTaskScreen()
    }

    private fun createNewTaskListStateFromCurrentOne(): TaskListState {
        return when(_uiState.value.taskListState) {
            is TaskListState.NoTask -> TaskListState.OnlyTodoExist(todoList)
            is TaskListState.OnlyTodoExist -> TaskListState.OnlyTodoExist(todoList)
            is TaskListState.OnlyDoneExist -> TaskListState.TodoAndDoneExist(todoList, doneList)
            is TaskListState.TodoAndDoneExist -> TaskListState.TodoAndDoneExist(todoList, doneList)
        }
    }
}