package com.bbasoglu.swipefun.splash

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.core.view.*
import androidx.navigation.ActivityNavigator
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import com.airbnb.lottie.LottieCompositionFactory
import com.bbasoglu.swipefun.R
import com.bbasoglu.swipefun.databinding.ActivitySplashBinding
import com.bbasoglu.swipefun.uimodule.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : BaseActivity(), BaseActivity.ActivityListener {

    private lateinit var navController: NavController

    private lateinit var binding: ActivitySplashBinding

    override val viewModel by viewModels<SplashActivityViewModel>()

    override fun getNavController(): NavController = navController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, true)

        setupView()
        setupNavHost()
        setupBackListener()
    }

    private fun setupView() {
        this.binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupNavHost() {
        val navHost =
            supportFragmentManager.findFragmentById(R.id.containerSplash) as NavHostFragment
        navController = navHost.navController
    }

    private fun setupBackListener() {
        onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    onBack()
                }
            }
        )
    }

    fun onBack() {
        when (navController.currentDestination?.id) {
            R.id.splashFragment -> {
                finish()
            }

            else -> {
                navController.navigateUp()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        try {
            LottieCompositionFactory.clearCache(this)
        } catch (ignored: Exception) {
        }
    }

    override fun onNavigate(navDirections: NavDirections, extras: ActivityNavigator.Extras?) {
        navigateTo(navDirections, extras)
    }

    override fun setBottomNavViewVisibility(isBottomNavViewVisible: Boolean) {
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
