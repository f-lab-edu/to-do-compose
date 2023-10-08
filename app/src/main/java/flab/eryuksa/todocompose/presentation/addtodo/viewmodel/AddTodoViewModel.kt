package flab.eryuksa.todocompose.presentation.addtodo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import flab.eryuksa.todocompose.data.repository.addtodo.AddTodoRepository
import flab.eryuksa.todocompose.presentation.addtodo.viewmodel.input.AddTodoInput
import flab.eryuksa.todocompose.presentation.addtodo.viewmodel.output.AddTodoEffect
import flab.eryuksa.todocompose.presentation.addtodo.viewmodel.output.AddTodoOutput
import flab.eryuksa.todocompose.presentation.addtodo.viewmodel.output.AddTodoState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTodoViewModel @Inject constructor(
    private val repository: AddTodoRepository
) : ViewModel(), AddTodoOutput, AddTodoInput {

    private val _uiState = MutableStateFlow(AddTodoState.EMPTY)
    override val uiState: StateFlow<AddTodoState>
        get() = _uiState

    private val _uiEffect = MutableSharedFlow<AddTodoEffect>(replay = 0)
    override val uiEffect: SharedFlow<AddTodoEffect>
        get() = _uiEffect

    override fun updateTitle(newTitle: String) {
        _uiState.value = _uiState.value.copy(title = newTitle)
    }

    override fun updateDetails(newDetails: String) {
        _uiState.value = _uiState.value.copy(details = newDetails)
    }

    override fun addTodo() {
        repository.addTodo(_uiState.value.title, _uiState.value.details)
        dismissScreen()
    }

    override fun dismissScreen() {
        viewModelScope.launch {
            _uiEffect.emit(AddTodoEffect.DismissAddTodoScreen)
        }
    }
}