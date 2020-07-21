package domain.model

data class MonumentDetailDomain(val id: Long,
                                val title: String,
                                val address: String,
                                val transport: String,
                                val email: String,
                                val geocoordinates: String,
                                val description: String,
                                val phone: String)