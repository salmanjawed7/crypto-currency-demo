package com.example.cleanarchitecture.domain.use_case.get_coin

import com.example.cleanarchitecture.common.Resource
import com.example.cleanarchitecture.data.remote.dto.coin_detail.toCoinDetail
import com.example.cleanarchitecture.data.remote.dto.coin_list.toCoin
import com.example.cleanarchitecture.domain.model.Coin
import com.example.cleanarchitecture.domain.model.CoinDetail
import com.example.cleanarchitecture.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {

    // use case should only expose 1 public function
    // 1 major functionality

    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading())
            val coin = repository.getCoinById(coinId).toCoinDetail()
            emit(Resource.Success(coin))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        } catch (e: Exception) {
            emit(Resource.Error("Internet Issue"))
        }
    }
}