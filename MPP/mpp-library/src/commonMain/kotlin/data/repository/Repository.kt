package data.repository

import domain.error.Either
import domain.error.Result
import domain.model.MonumentDetailDomain
import domain.model.MonumentMainListDomain

interface Repository {
    suspend fun getMonumentList(refreshStrategy: RefreshStrategy): Either<Result.Error, MonumentMainListDomain>
    suspend fun getMonumentItem(refreshStrategy: RefreshStrategy, id: String): Either<Result.Error, MonumentDetailDomain>
}