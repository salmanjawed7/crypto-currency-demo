package com.example.cleanarchitecture.data.remote

import com.example.cleanarchitecture.data.remote.dto.coin_detail.CoinDetailDto
import com.example.cleanarchitecture.data.remote.dto.coin_list.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinPaprikaApi {

    @GET("/v1/coins")
    suspend fun getCoins(): List<CoinDto>

    @GET("/v1/coins/{coinId}")
    suspend fun getCoinById(@Path("coinId") coinId: String): CoinDetailDto

}