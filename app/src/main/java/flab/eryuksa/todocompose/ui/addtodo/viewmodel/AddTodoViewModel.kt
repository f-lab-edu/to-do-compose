package flab.eryuksa.todocompose.ui.addtodo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import flab.eryuksa.todocompose.ui.addtodo.viewmodel.input.AddTodoInput
import flab.eryuksa.todocompose.ui.addtodo.viewmodel.output.AddTodoOutput
import flab.eryuksa.todocompose.ui.addtodo.viewmodel.output.AddTodoState
import flab.eryuksa.todocompose.ui.tasks.viewmodel.input.AddTodoJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AddTodoViewModel(private val addTodoJob: AddTodoJob) : ViewModel(), AddTodoOutput,
    AddTodoInput {

    private val _uiState = MutableStateFlow(AddTodoState.EMPTY)
    override val uiState: StateFlow<AddTodoState>
        get() = _uiState

    override fun updateTitle(newTitle: String) {
        _uiState.value = _uiState.value.copy(title = newTitle)
    }

    override fun updateDetails(newDetails: String) {
        _uiState.value = _uiState.value.copy(details = newDetails)
    }

    override fun addTodo() {
        addTodoJob.addTodo(_uiState.value.title, _uiState.value.details)
        resetUiState()
    }

    override fun cancelAddingTodo() {
        addTodoJob.cancelAddingTodo()
        resetUiState()
    }

    private fun resetUiState() {
        _uiState.value = AddTodoState.EMPTY
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
