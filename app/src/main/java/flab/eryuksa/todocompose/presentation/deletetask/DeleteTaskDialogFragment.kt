package flab.eryuksa.todocompose.presentation.deletetask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import flab.eryuksa.todocompose.presentation.deletetask.ui.DeleteTaskDialog
import flab.eryuksa.todocompose.presentation.deletetask.viewmodel.DeleteTaskViewModel
import flab.eryuksa.todocompose.presentation.theme.ToDoComposeTheme
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DeleteTaskDialogFragment : DialogFragment() {

    private val viewModel: DeleteTaskViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                ToDoComposeTheme {
                    DeleteTaskDialog(
                        onDismiss = viewModel::cancelDeletingTask,
                        onConfirm = viewModel::deleteTask,
                        taskTitle = viewModel.task.title
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
                viewModel.uiEffect.collectLatest {
                    dismiss()
                }
            }
        }
    }
}