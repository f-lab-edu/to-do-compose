package flab.eryuksa.todocompose.ui.addtodo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import flab.eryuksa.todocompose.ui.tasks.TasksViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AddTodoViewModel(private val tasksViewModel: TasksViewModel) : ViewModel() {

    private val _title = MutableStateFlow("")
    val title: StateFlow<String>
        get() = _title

    private val _details = MutableStateFlow("")
    val details: StateFlow<String>
        get() = _details

    fun updateTitle(newTitle: String) {
        _title.value = newTitle
    }

    fun updateDetails(newDetails: String) {
        _details.value = newDetails
    }

    fun addTodo() {
        tasksViewModel.addTodo(_title.value, _details.value)
    }

    fun cancelAddingTodo() {
        tasksViewModel.cancelAddingTodo()
    }
}

class AddTodoViewModelFactory(private val tasksViewModel: TasksViewModel) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddTodoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AddTodoViewModel(tasksViewModel) as T
        }
        throw IllegalArgumentException( "Unknown ViewModel Class" )
    }
}
