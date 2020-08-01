package data.repository

import data.local.LocalSource
import data.network.NetworkSource
import data.preferences.PreferencesSource

class CommonRepository(
    private val network: NetworkSource,
    private val local: LocalSource,
    private val preferences: PreferencesSource) : Repository {

}