package com.example.cleanarchitecture.data.repository

import com.example.cleanarchitecture.data.remote.CoinPaprikaApi
import com.example.cleanarchitecture.data.remote.dto.coin_detail.CoinDetailDto
import com.example.cleanarchitecture.data.remote.dto.coin_list.CoinDto
import com.example.cleanarchitecture.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi
) : CoinRepository {
    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }
}