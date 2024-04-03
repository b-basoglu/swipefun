package com.bbasoglu.swipefun.matchmaker.feed.domain.usecase

import com.bbasoglu.swipefun.matchmaker.feed.data.RickMortyGetCharactersRepository
import com.bbasoglu.swipefun.matchmaker.feed.domain.model.RickAndMortyCharacterIndexedDomainModel
import com.bbasoglu.swipefun.matchmaker.feed.domain.model.mapper.toPageIndexMapper
import com.bbasoglu.swipefun.matchmaker.feed.domain.model.mapper.toRickAndMortyCharacterDomainModel
import com.bbasoglu.swipefun.network.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RickMortyGetCharactersComposeUseCase  @Inject constructor(
    private val rickMortyRepository: RickMortyGetCharactersRepository,
) {
    suspend fun execute(page:Int): NetworkResponse<RickAndMortyCharacterIndexedDomainModel?> = try{
        withContext(Dispatchers.IO){
            when(val data =  rickMortyRepository.getCharacters(page)){
                is NetworkResponse.Error -> {
                    NetworkResponse.Error(data.message,null,data.code)
                }
                is NetworkResponse.Loading -> {
                    NetworkResponse.Loading()
                }
                is NetworkResponse.Success ->{
                    val nextNumber: Int = data.data?.info?.toPageIndexMapper(page + 1) ?: let {
                        page+1
                    }
                    NetworkResponse.Success(
                        RickAndMortyCharacterIndexedDomainModel(
                            nextNumber,
                            data.data?.results?.map {
                                it.toRickAndMortyCharacterDomainModel()
                            }
                        )
                    )
                }
            }
        }
    }catch (e:Exception){
        NetworkResponse.Error(null,null,null)
    }
}