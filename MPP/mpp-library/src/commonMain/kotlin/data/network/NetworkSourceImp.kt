package data.network

import data.api.ApiService
import domain.error.Either
import domain.error.Result
import domain.model.MonumentDetailDomain
import domain.model.MonumentMainListDomain

class NetworkSourceImp(private val apiService: ApiService): NetworkSource {
    override suspend fun getMonumentList(): Either<Result.Error, MonumentMainListDomain> = execute {
        apiService.getMonumentList()
    }

    override suspend fun getMonumentItem(id: String): Either<Result.Error, MonumentDetailDomain> = execute {
        apiService.getMonumentItem(id)
    }

}