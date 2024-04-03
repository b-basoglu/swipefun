package com.bbasoglu.swipefun.uimodule.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.ActivityNavigator
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.bbasoglu.swipefun.uimodule.extensions.hideKeyBoard
import com.bbasoglu.swipefun.uimodule.toolbar.StandardCustomToolbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class BaseFragment<VB : ViewBinding?> : Fragment() {

    protected abstract val viewModel: BaseViewModel

    private var activityListener: BaseActivity.ActivityListener? = null

    private var nullableBinding: VB? = null
    val binding get() = nullableBinding!!

    abstract fun getViewBinding(container: ViewGroup?): VB?

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        nullableBinding = getViewBinding(container)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        nullableBinding = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            activityListener = context as BaseActivity.ActivityListener
        } catch (e: ClassCastException) {
            e.printStackTrace()
        }
    }

    override fun onResume() {
        super.onResume()
        activityListener?.setBottomNavViewVisibility(isBottomNavViewVisible())
    }

    open fun isBottomNavViewVisible(): Boolean = false

    override fun onDetach() {
        activityListener = null
        super.onDetach()
    }

    fun navigateTo(
        navDirections: NavDirections? = null,
        extras: ActivityNavigator.Extras? = null
    ) {
        navDirections?.let {
            activityListener?.onNavigate(navDirections, extras)
        }
    }

    val onToolbarDefaultClick = object : StandardCustomToolbar.ToolbarIconClick {
        override fun onClick() {
            this@BaseFragment.hideKeyBoard()
            onBackPressed()
        }
    }

    fun onBackPressed() {
        findNavController().navigateUp()
    }

    fun launchScopeStarted(block: suspend CoroutineScope.() -> Unit) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                block()
            }
        }
    }
}
