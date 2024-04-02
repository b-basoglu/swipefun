package com.bbasoglu.swipefun.main

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.navigation.ActivityNavigator
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.bbasoglu.swipefun.R
import com.bbasoglu.swipefun.databinding.ActivityMainBinding
import com.bbasoglu.swipefun.uimodule.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity(), BaseActivity.ActivityListener {

    private lateinit var navController: NavController

    private lateinit var binding: ActivityMainBinding

    override val viewModel by viewModels<MainActivityViewModel>()

    override fun getNavController(): NavController = navController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bottomNavigationView = binding.navView
        val navHost = supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        navController = navHost.navController
        bottomNavigationView.setupWithNavController(navController)
        destinationController()
        onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    onBack()
                }
            }
        )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

    private fun destinationController() {
        navController.addOnDestinationChangedListener { _, _, _ ->
        }
    }

    override fun setBottomNavViewVisibility(isBottomNavViewVisible: Boolean) {
        binding.run {
            if (isBottomNavViewVisible) {
                navView.visibility = View.VISIBLE
            } else {
                navView.visibility = View.GONE
            }
        }
    }

    override fun onNavigate(navDirections: NavDirections, extras: ActivityNavigator.Extras?) {
        navigateTo(navDirections, extras)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    fun onBack() {

    }
}
