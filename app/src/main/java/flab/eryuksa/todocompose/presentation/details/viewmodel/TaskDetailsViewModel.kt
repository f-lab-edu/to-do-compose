package flab.eryuksa.todocompose.presentation.details.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import flab.eryuksa.todocompose.data.entity.Task
import flab.eryuksa.todocompose.data.repository.taskdetails.TaskDetailsRepository
import flab.eryuksa.todocompose.presentation.details.viewmodel.input.TaskDetailsInput
import flab.eryuksa.todocompose.presentation.details.viewmodel.output.TaskDetailsEffect
import flab.eryuksa.todocompose.presentation.details.viewmodel.output.TaskDetailsOutput
import flab.eryuksa.todocompose.presentation.details.viewmodel.output.TaskDetailsState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: TaskDetailsRepository
) : ViewModel(), TaskDetailsInput, TaskDetailsOutput {

    private val _uiState = MutableStateFlow(
        TaskDetailsState(savedStateHandle.get<Task>(TASK_DETAILS_TASK_KEY)!!)
    )
    override val uiState: StateFlow<TaskDetailsState>
        get() = _uiState

    private val _uiEffect = MutableSharedFlow<TaskDetailsEffect>(replay = 0)
    override val uiEffect: SharedFlow<TaskDetailsEffect>
        get() = _uiEffect

    override fun updateTaskAndGoToBackScreen() {
        viewModelScope.launch {
            repository.updateTaskToModifiedTask(_uiState.value.task)
            _uiEffect.emit(TaskDetailsEffect.GoBackScreen)
        }
    }

    override fun showDeleteTaskDialog() {
        viewModelScope.launch {
            _uiEffect.emit(TaskDetailsEffect.ShowDeleteTaskDialog(_uiState.value.task))
        }
    }

    override fun changeTaskDoneState() {
        val modifiedTask = with(_uiState.value.task) {
            copy(isDone = this.isDone.not())
        }
        viewModelScope.launch {
            repository.updateTaskToModifiedTask(modifiedTask)
            _uiState.value = TaskDetailsState(modifiedTask)
        }
    }

    override fun updateTitle(newTitle: String) {
        val updatedTask = _uiState.value.task.copy(title = newTitle)
        _uiState.value = TaskDetailsState(updatedTask)
    }

    override fun updateMemo(newMemo: String) {
        val updatedTask = _uiState.value.task.copy(memo = newMemo)
        _uiState.value = TaskDetailsState(updatedTask)
    }

    companion object {
        const val TASK_DETAILS_TASK_KEY = "TASK_DETAILS_TASK"
    }
}