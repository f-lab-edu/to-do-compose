package flab.eryuksa.todocompose.ui.addtodo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import flab.eryuksa.todocompose.ui.addtodo.viewmodel.input.AddTodoInput
import flab.eryuksa.todocompose.ui.addtodo.viewmodel.output.AddTodoEffect
import flab.eryuksa.todocompose.ui.addtodo.viewmodel.output.AddTodoOutput
import flab.eryuksa.todocompose.ui.addtodo.viewmodel.output.AddTodoState
import flab.eryuksa.todocompose.ui.tasks.viewmodel.input.AddTodoJob
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AddTodoViewModel(private val addTodoJob: AddTodoJob) : ViewModel(), AddTodoOutput,
    AddTodoInput {

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
        addTodoJob.addTodo(_uiState.value.title, _uiState.value.details)
        dismissScreen()
    }

    override fun dismissScreen() {
        viewModelScope.launch {
            _uiEffect.emit(AddTodoEffect.DismissAddTodoScreen)
        }
    }
}

class AddTodoViewModelFactory(private val addTodoJob: AddTodoJob) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddTodoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AddTodoViewModel(addTodoJob) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}
