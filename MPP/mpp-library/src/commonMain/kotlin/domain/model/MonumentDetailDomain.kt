package domain.model

import kotlinx.serialization.Serializable

@Serializable
data class MonumentDetailDomain(val id: Long,
                                val title: String,
                                val address: String,
                                val transport: String,
                                val email: String,
                                val geocoordinates: String,
                                val description: String,
                                val phone: String)