package flab.eryuksa.todocompose.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import flab.eryuksa.todocompose.R
import flab.eryuksa.todocompose.presentation.deletetask.viewmodel.DeleteTaskViewModel
import flab.eryuksa.todocompose.presentation.details.ui.TaskDetailsScreen
import flab.eryuksa.todocompose.presentation.details.viewmodel.TaskDetailsViewModel
import flab.eryuksa.todocompose.presentation.details.viewmodel.output.TaskDetailsEffect
import flab.eryuksa.todocompose.presentation.theme.ToDoComposeTheme
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TaskDetailsFragment : Fragment() {

    private val viewModel: TaskDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                ToDoComposeTheme() {
                    TaskDetailsScreen(
                        taskDetailsState = viewModel.uiState,
                        onClickBackButton = viewModel::goBack,
                        onClickDeleteButton = viewModel::deleteTask,
                        onClickCheckButton = viewModel::changeTaskDoneState,
                        onTitleChange = viewModel::updateTitle,
                        onMemoChange = viewModel::updateMemo
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
                        is TaskDetailsEffect.GoBack -> navController.navigateUp()
                        is TaskDetailsEffect.ShowDeleteTaskDialog -> {
                            val bundle = bundleOf(
                                DeleteTaskViewModel.TASK_TO_BE_DELETED_KEY to effect.taskToBeDeleted
                            )
                            navController.navigate(R.id.actionTaskDetailsToDeleteTask, bundle)
                        }
                    }
                }
            }
        }
    }
}