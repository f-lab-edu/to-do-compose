package flab.eryuksa.todocompose.ui.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import flab.eryuksa.todocompose.R
import flab.eryuksa.todocompose.ui.tasks.ui.TasksScreen
import flab.eryuksa.todocompose.ui.tasks.viewmodel.TasksViewModel
import flab.eryuksa.todocompose.ui.tasks.viewmodel.output.TasksEffect
import flab.eryuksa.todocompose.ui.theme.ToDoComposeTheme
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class TasksFragment : Fragment() {

    private val viewModel: TasksViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                ToDoComposeTheme {
                    TasksScreen(
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
        val navController = findNavController()

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.uiEffect.collectLatest { effect ->
                    if (effect is TasksEffect.ShowAddTodoScreen) {
                        navController.navigate(R.id.addTodoDialogFragment)
                    }
                }
            }
        }
    }
}