package data.model

import kotlinx.serialization.Serializable

@Serializable
data class MonumentMainItemData(val id: Long, val title: String, val geocoordinates:String)