package domain.model

import kotlinx.serialization.Serializable

@Serializable
data class MonumentMainListDomain(val list: List<MonumentMainItemDomain>)
