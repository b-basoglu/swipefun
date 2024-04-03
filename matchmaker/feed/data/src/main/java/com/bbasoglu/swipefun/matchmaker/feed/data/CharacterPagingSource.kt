package com.bbasoglu.swipefun.matchmaker.feed.data

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bbasoglu.swipefun.matchmaker.common.data.model.response.RickAndMortyCharacterResponseItem
import com.bbasoglu.swipefun.matchmaker.common.data.network.RickyMortyApiHelper
import com.bbasoglu.swipefun.network.NetworkResponse
import javax.inject.Inject

class CharacterPagingSource @Inject constructor(private val rickyMortyApiHelper: RickyMortyApiHelper) : PagingSource<Int, RickAndMortyCharacterResponseItem>() {
    override fun getRefreshKey(state: PagingState<Int, RickAndMortyCharacterResponseItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RickAndMortyCharacterResponseItem> = try {
        val nextPage: Int = params.key ?: FIRST_PAGE_INDEX
        when (val response = rickyMortyApiHelper.getCharacters(nextPage)) {
                is NetworkResponse.Success -> {
                    var nextPageNumber : Int? = null

                    response.data?.results?.let {
                        if (response.data?.info?.next != null){
                            val uri = Uri.parse(response.data?.info?.next)
                            val nextPageQuery = uri.getQueryParameter("page")
                            nextPageNumber = nextPageQuery?.toInt()
                        }
                        LoadResult.Page(data = it, prevKey = null, nextKey = nextPageNumber)
                    }?:let {
                        LoadResult.Error(IllegalStateException("Empty list"))
                    }
                }
                else ->{
                    LoadResult.Error(IllegalStateException("Response failed"))
                }
            }
        }catch (e:Exception){
            LoadResult.Error(IllegalStateException("Unknown"))
        }

    companion object{
        private const val FIRST_PAGE_INDEX = 1
    }
}