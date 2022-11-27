package com.example.cleanarchitecture.domain.repository

import com.example.cleanarchitecture.data.remote.dto.coin_detail.CoinDetailDto
import com.example.cleanarchitecture.data.remote.dto.coin_list.CoinDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailDto
}