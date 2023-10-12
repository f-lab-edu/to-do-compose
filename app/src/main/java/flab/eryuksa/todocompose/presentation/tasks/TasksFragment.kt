package flab.eryuksa.todocompose.presentation.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import flab.eryuksa.todocompose.R
import flab.eryuksa.todocompose.presentation.deletetask.viewmodel.DeleteTaskViewModel
import flab.eryuksa.todocompose.presentation.tasks.ui.TasksScreen
import flab.eryuksa.todocompose.presentation.tasks.viewmodel.TasksViewModel
import flab.eryuksa.todocompose.presentation.tasks.viewmodel.output.TasksEffect
import flab.eryuksa.todocompose.presentation.theme.ToDoComposeTheme
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
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
                    when (effect) {
                        is TasksEffect.ShowAddTodoScreen ->
                            navController.navigate(R.id.actionTasksToAddTodoDialog)
                        is TasksEffect.ShowDeleteTaskScreen -> {
                            val bundle = bundleOf(
                                DeleteTaskViewModel.TASK_TO_BE_DELETED_KEY to effect.taskToBeDeleted
                            )
                            navController.navigate(R.id.actionTasksToDeleteTaskDialog, bundle)
                        }
                    }
                }
            }
        }
    }
}