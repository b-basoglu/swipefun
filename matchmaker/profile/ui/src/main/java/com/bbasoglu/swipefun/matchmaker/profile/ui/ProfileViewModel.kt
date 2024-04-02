package com.bbasoglu.swipefun.matchmaker.profile.ui

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.bbasoglu.swipefun.di.IoDispatcher
import com.bbasoglu.swipefun.matchmaker.profile.domain.usecase.RickMortyGetLikesUseCase
import com.bbasoglu.swipefun.matchmaker.profile.ui.adapter.ProfileAdapter
import com.bbasoglu.swipefun.matchmaker.profile.ui.adapter.model.mapper.toProfileUiData
import com.bbasoglu.swipefun.uimodule.adapter.base.BaseAdapterData
import com.bbasoglu.swipefun.uimodule.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    val rickMortyGetLikesUseCase: RickMortyGetLikesUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BaseViewModel(){
    private var getCharactersJob: Job? = null

    private val _feedState: MutableStateFlow<PagingData<BaseAdapterData>> = MutableStateFlow(value = PagingData.empty())
    val feedState: StateFlow<PagingData<BaseAdapterData>> = _feedState

    fun getLikedList() {
        getCharactersJob?.cancel()
        getCharactersJob = viewModelScope.launch {
            try {
                withContext(dispatcher){
                    rickMortyGetLikesUseCase.execute().cachedIn(viewModelScope).collectLatest { consumed ->
                        _feedState.value = consumed.map {
                            it.toProfileUiData()
                        }

                    }
                }
            }catch (e:Exception){

            }
        }
    }

    override fun onCleared() {
        getCharactersJob?.cancel()
        super.onCleared()
    }

}
