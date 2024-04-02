package com.bbasoglu.swipefun.uimodule.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.ActivityNavigator
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.bbasoglu.swipefun.uimodule.extensions.hideKeyBoard
import com.bbasoglu.swipefun.uimodule.toolbar.StandardCustomToolbar

abstract class BaseDialogFragment<VB : ViewBinding?> : DialogFragment() {

    protected abstract val viewModel: BaseViewModel

    var activityListener: BaseActivity.ActivityListener? = null

    var mViewBinding: VB? = null

    abstract fun getViewBinding(): VB?

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewBinding = getViewBinding()
        return mViewBinding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mViewBinding = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            activityListener = context as BaseActivity.ActivityListener
        } catch (e: ClassCastException) {
            e.printStackTrace()
        }
    }

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
            this@BaseDialogFragment.hideKeyBoard()
            onBackPressed()
        }
    }

    fun onBackPressed() {
        findNavController().navigateUp()
    }
}
