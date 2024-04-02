package com.bbasoglu.swipefun.matchmaker.feed.ui

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.bbasoglu.swipefun.di.IoDispatcher
import com.bbasoglu.swipefun.matchmaker.feed.domain.usecase.RickMortyGetCharactersUseCase
import com.bbasoglu.swipefun.matchmaker.feed.domain.usecase.RickMortyLikesInsertUseCase
import com.bbasoglu.swipefun.matchmaker.feed.ui.adapter.FeedAdapter
import com.bbasoglu.swipefun.matchmaker.feed.ui.adapter.model.FeedData
import com.bbasoglu.swipefun.matchmaker.feed.ui.adapter.model.mapper.toFeedData
import com.bbasoglu.swipefun.matchmaker.feed.ui.adapter.model.mapper.toRickAndMortyCharacterDomainModel
import com.bbasoglu.swipefun.uimodule.adapter.base.BaseAdapterData
import com.bbasoglu.swipefun.uimodule.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val rickMortyGetCharactersUseCase: RickMortyGetCharactersUseCase,
    private val rickMortyLikesInsertUseCase: RickMortyLikesInsertUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BaseViewModel(){

    private val _feedState: MutableStateFlow<PagingData<BaseAdapterData>> = MutableStateFlow(value = PagingData.empty())
    val feedState: StateFlow<PagingData<BaseAdapterData>> = _feedState

    private var getCharactersJob: Job? = null

    private var feedItemList = ArrayList<FeedData>()

    fun getCharacters() {
        getCharactersJob?.cancel()
        getCharactersJob = viewModelScope.launch {
            try {
                withContext(dispatcher){
                    rickMortyGetCharactersUseCase.execute()
                        .distinctUntilChanged()
                        .cachedIn(viewModelScope).collectLatest { consumed ->
                        _feedState.value = consumed.map {domainModel ->
                            domainModel.toFeedData().also {feedData ->
                                feedItemList.add(feedData)
                            }
                        }
                    }
                }
            }catch (e:Exception){
            }
        }
    }

    fun insertRickMortyLike(position: Int){
        viewModelScope.launch {
            feedItemList.getOrNull(position)?.let {feedData->
                rickMortyLikesInsertUseCase.execute(RickMortyLikesInsertUseCase.Param(feedData.toRickAndMortyCharacterDomainModel()))
            }
        }
    }

    override fun onCleared() {
        getCharactersJob?.cancel()
        super.onCleared()
    }

}
