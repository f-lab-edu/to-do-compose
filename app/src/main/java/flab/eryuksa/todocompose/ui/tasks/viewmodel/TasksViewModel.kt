package flab.eryuksa.todocompose.ui.tasks.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import flab.eryuksa.todocompose.ui.tasks.model.Task
import flab.eryuksa.todocompose.ui.tasks.viewmodel.input.AddTodoJob
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

    private var todoList = emptyList<Task>()
    private var doneList = emptyList<Task>()

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

    override fun addTodo(title: String, details: String) {
        addTask(Task(title, details, isDone = false))
        updateTasksState()
    }

    private fun updateTasksState() {
        _uiState.value = createUpdatedTaskListState()
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

    private fun addTask(task: Task) {
        if (task.isDone) {
            doneList += task
        } else {
            todoList += task
        }
    }

    private fun removeTask(task: Task) {
        if (task.isDone) {
            doneList = doneList.filter { it != task }
        } else {
            todoList = todoList.filter { it != task }
        }
    }
}