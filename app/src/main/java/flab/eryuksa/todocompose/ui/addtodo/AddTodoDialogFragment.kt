package flab.eryuksa.todocompose.ui.addtodo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import flab.eryuksa.todocompose.ui.addtodo.ui.AddTodoScreen
import flab.eryuksa.todocompose.ui.addtodo.viewmodel.AddTodoViewModel
import flab.eryuksa.todocompose.ui.addtodo.viewmodel.AddTodoViewModelFactory
import flab.eryuksa.todocompose.ui.addtodo.viewmodel.output.AddTodoEffect
import flab.eryuksa.todocompose.ui.tasks.viewmodel.TasksViewModel
import flab.eryuksa.todocompose.ui.theme.ToDoComposeTheme
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class AddTodoDialogFragment : DialogFragment() {

    private val viewModel: AddTodoViewModel by viewModels {
        val tasksViewModel: TasksViewModel by activityViewModels()
        AddTodoViewModelFactory(tasksViewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                ToDoComposeTheme {
                    AddTodoScreen(
                        input = viewModel,
                        output = viewModel
                    )
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeUiEffect()
    }

    private fun observeUiEffect() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.uiEffect.collectLatest { effect ->
                    if (effect is AddTodoEffect.DismissAddTodoScreen) {
                        dismiss()
                    }
                }
            }
        }
    }
}