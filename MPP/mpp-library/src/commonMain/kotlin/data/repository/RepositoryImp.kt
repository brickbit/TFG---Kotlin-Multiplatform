package data.repository

import data.local.LocalSource
import data.network.NetworkSource
import data.preferences.PreferencesSource
import domain.error.Either
import domain.error.Result
import domain.model.MonumentDetailDomain
import domain.model.MonumentMainListDomain

class RepositoryImp(
    private val network: NetworkSource,
    private val local: LocalSource,
    private val preferences: PreferencesSource) : Repository {

    override suspend fun getMonumentList(refreshStrategy: RefreshStrategy): Either<Result.Error, MonumentMainListDomain> =
        when (refreshStrategy) {
            RefreshStrategy.NETWORK_AND_SAVE_LOCAL -> network.getMonumentList()
            RefreshStrategy.LOCAL -> network.getMonumentList()
            RefreshStrategy.NETWORK -> network.getMonumentList()
        }

    override suspend fun getMonumentItem(refreshStrategy: RefreshStrategy, id: String): Either<Result.Error, MonumentDetailDomain> =
        when (refreshStrategy) {
            RefreshStrategy.NETWORK_AND_SAVE_LOCAL -> network.getMonumentItem(id)
            RefreshStrategy.LOCAL -> network.getMonumentItem(id)
            RefreshStrategy.NETWORK -> network.getMonumentItem(id)
        }
}