package com.bbasoglu.swipefun.matchmaker.feed.composeui

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bbasoglu.swipefun.matchmaker.feed.composeui.navigation.NavGraph
import com.bbasoglu.swipefun.matchmaker.feed.composeui.util.ColorPrimary
import com.bbasoglu.swipefun.matchmaker.feed.composeui.util.ColorPrimaryDark
import com.bbasoglu.swipefun.matchmaker.feed.composeui.databinding.FragmentFeedComposeBinding
import com.bbasoglu.swipefun.uimodule.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FeedScreenComposeFragment : BaseFragment<FragmentFeedComposeBinding>() {


    override val viewModel by viewModels<FeedScreenComposeFragmentViewModel>()

    override fun getViewBinding(container: ViewGroup?): FragmentFeedComposeBinding =
        FragmentFeedComposeBinding.inflate(layoutInflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            setContent()
        }
    }

    private fun setContent() {
        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = if (isSystemInDarkTheme()){
                                ColorPrimaryDark
                            }else{
                                ColorPrimary
                            }
                    ) {
                        NavGraph(viewModel)
                    }
                }
            }
        }
    }

    override fun isBottomNavViewVisible(): Boolean {
        return true
    }
}