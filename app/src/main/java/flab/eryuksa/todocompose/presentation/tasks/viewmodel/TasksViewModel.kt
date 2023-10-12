package flab.eryuksa.todocompose.presentation.tasks.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import flab.eryuksa.todocompose.data.entity.Task
import flab.eryuksa.todocompose.data.repository.tasks.TasksRepository
import flab.eryuksa.todocompose.presentation.tasks.viewmodel.input.TasksInput
import flab.eryuksa.todocompose.presentation.tasks.viewmodel.output.TasksEffect
import flab.eryuksa.todocompose.presentation.tasks.viewmodel.output.TasksOutput
import flab.eryuksa.todocompose.presentation.tasks.viewmodel.output.TasksState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val repository: TasksRepository
) : ViewModel(), TasksOutput, TasksInput {

    override val uiState: StateFlow<TasksState> = combine(
        repository.getTodoTasks(), repository.getDoneTasks()
    ) { todoList, doneList ->
        TasksState(todoList = todoList, doneList = doneList)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = TasksState.INITIAL
    )

    private val _uiEffect = MutableSharedFlow<TasksEffect>(replay = 0)
    override val uiEffect: SharedFlow<TasksEffect>
        get() = _uiEffect

    override fun showAddTaskScreen() {
        viewModelScope.launch {
            _uiEffect.emit(TasksEffect.ShowAddTodoScreen)
        }
    }

    override fun changeCheckedState(task: Task) {
        viewModelScope.launch {
            repository.updateTaskToModifiedTask(
                task.copy(isDone = task.isDone.not())
            )
        }
    }

    override fun showDeleteTaskDialog(task: Task) {
        viewModelScope.launch {
            _uiEffect.emit(TasksEffect.ShowDeleteTaskScreen(taskToBeDeleted = task))
        }
    }
}