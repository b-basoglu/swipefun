package com.bbasoglu.swipefun.matchmaker.feed.composeui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bbasoglu.swipefun.matchmaker.feed.composeui.model.FeedData
import com.bbasoglu.swipefun.matchmaker.feed.composeui.model.mapper.toFeedData
import com.bbasoglu.swipefun.matchmaker.feed.composeui.model.mapper.toRickAndMortyCharacterDomainModel
import com.bbasoglu.swipefun.matchmaker.feed.domain.usecase.RickMortyGetCharactersComposeUseCase
import com.bbasoglu.swipefun.matchmaker.feed.domain.usecase.RickMortyLikesInsertUseCase
import com.bbasoglu.swipefun.network.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FeedScreenComposeViewModel @Inject constructor(
    private val rickMortyGetCharactersUseCase: RickMortyGetCharactersComposeUseCase,
    private val rickMortyLikesInsertUseCase: RickMortyLikesInsertUseCase
) : ViewModel() {

    private val _feedState: MutableStateFlow<ArrayList<FeedData>> = MutableStateFlow(ArrayList())
    val feedState: StateFlow<ArrayList<FeedData>> = _feedState

    private var page = 1

    init {
        viewModelScope.launch {
            getCharacters()
        }
    }

    suspend fun getCharacters() {
        viewModelScope.launch {
            _feedState.value = ArrayList()
            when (val data = rickMortyGetCharactersUseCase.execute(page)) {
                is NetworkResponse.Success -> {
                    data.data?.rickAndMortyCharacterDomainModelList?.let { characters ->
                        _feedState.value = ArrayList(characters.map { it.toFeedData() })
                        page = data.data?.page?:page++
                    }
                }
                is NetworkResponse.Error -> {
                    // Handle error
                }
                is NetworkResponse.Loading -> {
                    // Handle loading
                }
            }
        }

    }

    fun insertRickMortyLike(feedData: FeedData) {
        viewModelScope.launch {
            rickMortyLikesInsertUseCase.execute(
                RickMortyLikesInsertUseCase.Param(feedData.toRickAndMortyCharacterDomainModel())
            )
        }
    }
}