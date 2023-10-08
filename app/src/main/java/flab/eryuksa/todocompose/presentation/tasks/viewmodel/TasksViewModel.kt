package flab.eryuksa.todocompose.presentation.tasks.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import flab.eryuksa.todocompose.data.model.Task
import flab.eryuksa.todocompose.data.repository.tasks.TasksRepository
import flab.eryuksa.todocompose.presentation.tasks.viewmodel.input.TasksInput
import flab.eryuksa.todocompose.presentation.tasks.viewmodel.output.TasksEffect
import flab.eryuksa.todocompose.presentation.tasks.viewmodel.output.TasksOutput
import flab.eryuksa.todocompose.presentation.tasks.viewmodel.output.TasksState
import kotlinx.coroutines.flow.MutableSharedFlow
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
        createTasksState(todoList, doneList)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = TasksState.NoTask
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
        repository.updateTaskToModifiedTask(
            task.copy(isDone = task.isDone.not())
        )
    }

    private fun createTasksState(todoList: List<Task>, doneList: List<Task>): TasksState {
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
}