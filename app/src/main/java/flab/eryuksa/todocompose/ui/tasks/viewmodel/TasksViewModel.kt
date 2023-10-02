package flab.eryuksa.todocompose.ui.tasks.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import flab.eryuksa.todocompose.ui.tasks.viewmodel.input.AddTodoJob
import flab.eryuksa.todocompose.ui.tasks.model.Task
import flab.eryuksa.todocompose.ui.tasks.viewmodel.input.TasksInput
import flab.eryuksa.todocompose.ui.tasks.viewmodel.output.TasksEffect
import flab.eryuksa.todocompose.ui.tasks.viewmodel.output.TasksOutput
import flab.eryuksa.todocompose.ui.tasks.viewmodel.output.TasksState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TasksViewModel : ViewModel(), TasksOutput, TasksInput, AddTodoJob {

    private val _uiState = MutableStateFlow<TasksState>(TasksState.NoTask)
    override val uiState: StateFlow<TasksState>
        get() = _uiState

    private val _uiEffect = MutableSharedFlow<TasksEffect>(replay = 0)
    override val uiEffect: SharedFlow<TasksEffect>
        get() = _uiEffect

    private val todoList = mutableListOf<Task>()
    private val doneList = mutableListOf<Task>()

    override fun showAddTaskScreen() {
        viewModelScope.launch {
            _uiEffect.emit(TasksEffect.ShowAddTodoScreen)
        }
    }

    override fun changeCheckState(task: Task) {
        removeTask(task)
        addTask(task.copy(isDone = task.isDone.not()))
        updateTasksState()
    }

    private fun dismissAddTaskScreen() {
        viewModelScope.launch {
            _uiEffect.emit(TasksEffect.DismissAddTodoScreen)
        }
    }

    override fun addTodo(title: String, details: String) {
        addTask(Task(title, details, isDone = false))
        updateTasksState()
        dismissAddTaskScreen()
    }

    override fun cancelAddingTodo() {
        dismissAddTaskScreen()
    }

    private fun createUpdatedTaskListState(): TasksState {
        return when {
            todoList.isEmpty() && doneList.isEmpty() -> TasksState.NoTask
            todoList.isNotEmpty() && doneList.isNotEmpty() -> TasksState.TodoAndDoneExist(
                todoList,
                doneList
            )
            todoList.isNotEmpty() -> TasksState.OnlyTodoExist(todoList)
            else -> TasksState.OnlyDoneExist(doneList)
        }
    }

    private fun updateTasksState() {
        _uiState.value = createUpdatedTaskListState()
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