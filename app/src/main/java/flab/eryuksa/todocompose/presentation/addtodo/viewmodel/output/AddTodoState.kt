package flab.eryuksa.todocompose.presentation.addtodo.viewmodel.output

data class AddTodoState(
    val title: String,
    val details: String
) {
    companion object {
        val EMPTY = AddTodoState("", "")
    }
}