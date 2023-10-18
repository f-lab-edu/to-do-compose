package flab.eryuksa.todocompose.presentation.deletetask.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import flab.eryuksa.todocompose.data.entity.Task
import flab.eryuksa.todocompose.data.repository.deletetask.DeleteTaskRepository
import flab.eryuksa.todocompose.presentation.deletetask.viewmodel.input.DeleteTaskInput
import flab.eryuksa.todocompose.presentation.deletetask.viewmodel.output.DeleteTaskEffect
import flab.eryuksa.todocompose.presentation.deletetask.viewmodel.output.DeleteTaskOutput
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeleteTaskViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: DeleteTaskRepository
): ViewModel(), DeleteTaskInput, DeleteTaskOutput {

    override val task: Task = savedStateHandle[TASK_TO_BE_DELETED_KEY]!!

    private val _uiEffect = MutableSharedFlow<DeleteTaskEffect>()
    override val uiEffect: SharedFlow<DeleteTaskEffect>
        get() = _uiEffect

    override fun cancelDeletingTask() {
        viewModelScope.launch {
            _uiEffect.emit(DeleteTaskEffect.DismissDeleteTaskScreen)
        }
    }

    override fun deleteTask() {
        viewModelScope.launch {
            repository.deleteTask(task)
            _uiEffect.emit(DeleteTaskEffect.DismissDeleteTaskScreen)
        }
    }

    companion object {
        const val TASK_TO_BE_DELETED_KEY = "TASK_TO_BE_DELETED"
    }
}