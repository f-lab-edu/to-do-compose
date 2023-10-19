package flab.eryuksa.todocompose.presentation.details.viewmodel.input

interface TaskDetailsInput {

    fun updateTaskAndGoToBackScreen()
    fun showDeleteTaskDialog()
    fun changeTaskDoneState()
    fun updateTitle(newTitle: String)
    fun updateMemo(newMemo: String)
}