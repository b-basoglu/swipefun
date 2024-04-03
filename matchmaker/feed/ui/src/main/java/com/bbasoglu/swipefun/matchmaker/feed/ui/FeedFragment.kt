package com.bbasoglu.swipefun.matchmaker.feed.ui

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.view.animation.LinearInterpolator
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import com.bbasoglu.swipefun.matchmaker.feed.ui.adapter.FeedAdapter
import com.bbasoglu.swipefun.matchmaker.feed.ui.adapter.model.FeedData
import com.bbasoglu.swipefun.matchmaker.feed.ui.databinding.FragmentFeedBinding
import com.bbasoglu.swipefun.uimodule.base.BaseFragment
import com.bbasoglu.swipefun.uimodule.cardstackview.CardStackLayoutManager
import com.bbasoglu.swipefun.uimodule.cardstackview.CardStackListener
import com.bbasoglu.swipefun.uimodule.cardstackview.Direction
import com.bbasoglu.swipefun.uimodule.cardstackview.Duration
import com.bbasoglu.swipefun.uimodule.cardstackview.RewindAnimationSetting
import com.bbasoglu.swipefun.uimodule.cardstackview.StackFrom
import com.bbasoglu.swipefun.uimodule.cardstackview.SwipeAbleMethod
import com.bbasoglu.swipefun.uimodule.cardstackview.SwipeAnimationSetting
import com.bbasoglu.swipefun.uimodule.cardstackview.internal.CardStackSnapHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FeedFragment : BaseFragment<FragmentFeedBinding>() {

    private var feedAdapter: FeedAdapter? = null

    private var manager: CardStackLayoutManager? = null

    override val viewModel by viewModels<FeedViewModel>()

    override fun getViewBinding(container: ViewGroup?): FragmentFeedBinding =
        FragmentFeedBinding.inflate(layoutInflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        feedAdapter = FeedAdapter(::resultClicked)
        setUpRecyclerView()
        viewModel.getCharacters()
        setupButton()
        initObservers()
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.feedState.collectLatest {
                    feedAdapter?.submitData(it)
                }
            }
        }
    }

    fun insertLike(position: Int){
        viewModel.insertRickMortyLike(position = position)
    }

    private fun setUpRecyclerView() {
        manager = CardStackLayoutManager(object : CardStackListener{
            var lastPosition = 0

            override fun onCardDragging(direction: Direction?, ratio: Float) {

            }

            override fun onCardSwiped(direction: Direction?) {
                when(direction){
                    Direction.Right -> {
                        insertLike(lastPosition)
                    }
                    else->{

                    }
                }
            }

            override fun onCardRewound() {

            }

            override fun onCardCanceled() {

            }

            override fun onCardAppeared(view: View?, position: Int) {
                lastPosition = position

            }

            override fun onCardDisappeared(view: View?, position: Int) {

            }
        }
        ).also { manager ->
            manager.setStackFrom(StackFrom.None)
            manager.setVisibleCount(3)
            manager.setTranslationInterval(8.0f)
            manager.setScaleInterval(0.95f)
            manager.setSwipeThreshold(0.3f)
            manager.setMaxDegree(20.0f)
            manager.setDirections(Direction.HORIZONTAL)
            manager.setCanScrollHorizontal(true)
            manager.setSwipeableMethod(SwipeAbleMethod.AutomaticAndManual)
            manager.setOverlayInterpolator(LinearInterpolator())
        }
        binding.recyclerview.run {
            layoutManager = manager
            adapter = feedAdapter
            overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            CardStackSnapHelper().attachToRecyclerView(this)
            itemAnimator.apply {
                if (this is DefaultItemAnimator) {
                    supportsChangeAnimations = false
                }
            }
        }
    }


    private fun setupButton() {
        binding.skipButton.setOnClickListener {
            val setting = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Left)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(AccelerateInterpolator())
                .build()
            manager?.setSwipeAnimationSetting(setting)
            swipe()
        }

        binding.rewindButton.setOnClickListener {
            val setting = RewindAnimationSetting.Builder()
                .setDirection(Direction.Bottom)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(DecelerateInterpolator())
                .build()
            manager?.setRewindAnimationSetting(setting)
            rewind()
        }

        binding.likeButton.setOnClickListener {
            val setting = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Right)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(AccelerateInterpolator())
                .build()
            manager?.setSwipeAnimationSetting(setting)
            swipe()
        }
    }
    private fun swipe() {
        manager?.let {
            binding.recyclerview.smoothScrollToPosition(it.topPosition + 1)
        }
    }

    private fun rewind() {
        manager?.let {
            binding.recyclerview.smoothScrollToPosition(it.topPosition - 1)
        }
    }


    private fun resultClicked(data: Any) {
        when(data){
            is FeedData ->{
                Toast.makeText(requireContext(), "${data.name} clicked", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun isBottomNavViewVisible(): Boolean {
        return true
    }


}
