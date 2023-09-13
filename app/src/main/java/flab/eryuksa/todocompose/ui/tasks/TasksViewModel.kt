package flab.eryuksa.todocompose.ui.tasks

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TasksViewModel : ViewModel() {

    private val _isAddTaskDialogShown = MutableStateFlow(false)
    val isAddTaskDialogShown: StateFlow<Boolean>
        get() = _isAddTaskDialogShown

    private val _taskListState = MutableStateFlow(TaskListState.NoTask)
    val taskListState: StateFlow<TaskListState>
        get() = _taskListState

    fun showAddTaskScreen() {
        _isAddTaskDialogShown.value = true
    }

    fun dismissAddTaskScreen() {
        _isAddTaskDialogShown.value = false
    }


}