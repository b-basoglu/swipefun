package com.bbasoglu.swipefun.matchmaker.profile.ui

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bbasoglu.swipefun.matchmaker.profile.ui.adapter.ProfileAdapter
import com.bbasoglu.swipefun.matchmaker.profile.ui.adapter.ProfileAdapterClickEvent
import com.bbasoglu.swipefun.matchmaker.profile.ui.adapter.model.ProfileUiData
import com.bbasoglu.swipefun.matchmaker.profile.ui.databinding.FragmentProfileBinding
import com.bbasoglu.swipefun.uimodule.adapter.decoration.StaggeredGridItemDecoration
import com.bbasoglu.swipefun.uimodule.base.BaseFragment
import com.bbasoglu.swipefun.uimodule.extensions.dp2px
import com.bbasoglu.swipefun.uimodule.toolbar.StandardToolbarData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    private var profileAdapter: ProfileAdapter? = null


    override val viewModel by viewModels<ProfileViewModel>()

    override fun getViewBinding(container: ViewGroup?): FragmentProfileBinding =
        FragmentProfileBinding.inflate(layoutInflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profileAdapter = ProfileAdapter(::resultClicked)
        setToolbar()
        setUpRecyclerView()
        profileAdapter?.let {
            viewModel.getLikedList()
        }
        initObservers()
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.feedState.collectLatest {
                    profileAdapter?.submitData(it)
                }
            }
        }
    }

    private fun setUpRecyclerView() {
        binding.recyclerView.run {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = profileAdapter
            setHasFixedSize(true)
            addItemDecoration(
                StaggeredGridItemDecoration(
                    6.dp2px,
                    12.dp2px,
                    12.dp2px,
                    6.dp2px,
                    6.dp2px
                )
            )
        }
    }


    override fun isBottomNavViewVisible(): Boolean {
        return true
    }


    private fun resultClicked(data: Any) {
        when(data){
            is ProfileAdapterClickEvent ->{
                when(data){
                    is ProfileAdapterClickEvent.ProfileItemClicked -> {
                        Toast.makeText(requireContext(), "${data.profileUiData.name} clicked", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun setToolbar() {
        binding.run {
            toolbar.setToolbar(
                StandardToolbarData(
                    title = getString(com.bbasoglu.swipefun.common.ui.R.string.profile),
                    titleColorId = com.bbasoglu.swipefun.uimodule.R.color.textColorPrimary,
                    isTitleCenter = true,
                )
            )
        }
    }
}
