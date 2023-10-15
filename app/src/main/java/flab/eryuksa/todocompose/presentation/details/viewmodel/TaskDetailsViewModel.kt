package flab.eryuksa.todocompose.presentation.details.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import flab.eryuksa.todocompose.data.entity.Task
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
    savedStateHandle: SavedStateHandle
) : ViewModel(), TaskDetailsInput, TaskDetailsOutput {

    private val _uiState = MutableStateFlow(
        TaskDetailsState(savedStateHandle.get<Task>(TASK_DETAILS_TASK_KEY)!!)
    )
    override val uiState: StateFlow<TaskDetailsState>
        get() = _uiState

    private val _uiEffect = MutableSharedFlow<TaskDetailsEffect>(replay = 0)
    override val uiEffect: SharedFlow<TaskDetailsEffect>
        get() = _uiEffect

    override fun goBack() {
        viewModelScope.launch {
            _uiEffect.emit(TaskDetailsEffect.GoBack)
        }
    }

    override fun deleteTask() {
    }

    override fun changeTaskDoneState() {
        val task = _uiState.value.task
        _uiState.value = TaskDetailsState(
            task.copy(isDone = task.isDone.not())
        )
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