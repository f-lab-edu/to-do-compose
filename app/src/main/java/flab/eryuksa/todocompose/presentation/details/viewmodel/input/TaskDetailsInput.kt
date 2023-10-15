package flab.eryuksa.todocompose.presentation.details.viewmodel.input

interface TaskDetailsInput {

    fun goBack()
    fun deleteTask()
    fun changeTaskDoneState()
    fun updateTitle(newTitle: String)
    fun updateMemo(newMemo: String)
}