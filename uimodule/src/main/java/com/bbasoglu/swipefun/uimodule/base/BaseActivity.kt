package com.bbasoglu.swipefun.uimodule.base

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ActivityNavigator
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import com.bbasoglu.swipefun.uimodule.extensions.safeNavigate

abstract class BaseActivity : AppCompatActivity() {

    protected abstract val viewModel: BaseViewModel

    abstract fun getNavController(): NavController

    interface ActivityListener {
        fun onNavigate(
            navDirections: NavDirections,
            extras: ActivityNavigator.Extras? = null
        )

        fun setBottomNavViewVisibility(isBottomNavViewVisible: Boolean)

    }

    fun navigateTo(
        navDirections: NavDirections? = null,
        extras: ActivityNavigator.Extras? = null,
        deeplink: Uri? = null
    ) {
        getNavController().safeNavigate(navDirections, extras, deeplink)
    }
}
