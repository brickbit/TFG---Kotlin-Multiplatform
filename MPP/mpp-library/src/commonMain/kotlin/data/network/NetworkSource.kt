package data.network

import domain.error.Either
import domain.error.Result
import domain.model.MonumentDetailDomain
import domain.model.MonumentMainListDomain

interface NetworkSource {
    suspend fun getMonumentList(): Either<Result.Error, MonumentMainListDomain>

    suspend fun getMonumentItem(id: String): Either<Result.Error, MonumentDetailDomain>
}