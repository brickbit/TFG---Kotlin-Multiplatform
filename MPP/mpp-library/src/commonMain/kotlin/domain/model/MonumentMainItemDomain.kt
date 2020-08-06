package domain.model

import kotlinx.serialization.Serializable

@Serializable
data class MonumentMainItemDomain(val id: Long, val title: String, val geocoordinates:String)