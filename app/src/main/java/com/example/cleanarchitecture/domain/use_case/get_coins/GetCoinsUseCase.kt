package com.example.cleanarchitecture.domain.use_case.get_coins

import com.example.cleanarchitecture.common.Resource
import com.example.cleanarchitecture.data.remote.dto.coin_list.toCoin
import com.example.cleanarchitecture.domain.model.Coin
import com.example.cleanarchitecture.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {

    // use case should only expose 1 public function
    // 1 major functionality

    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading())
            val coins = repository.getCoins().map { it.toCoin() }
            emit(Resource.Success(coins))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        } catch (e: Exception) {
            emit(Resource.Error("Internet Issue"))
        }

    }
}