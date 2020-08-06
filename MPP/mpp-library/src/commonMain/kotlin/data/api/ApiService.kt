package data.api

import domain.model.MonumentDetailDomain
import domain.model.MonumentMainListDomain
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.http.Url
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

class ApiService {
    private val client = HttpClient()

    private var address = Url("https://t21services.herokuapp.com/points")

    suspend fun getMonumentList(): MonumentMainListDomain =
        Json(JsonConfiguration.Stable).let { json ->
            json.parse(MonumentMainListDomain.serializer(), client.get {
                url(address.toString())
            })
        }

    suspend fun getMonumentItem(id: String): MonumentDetailDomain =
        Json(JsonConfiguration.Stable).let { json ->
            json.parse(MonumentDetailDomain.serializer(), client.get {
                url(address.toString() + "/" + id)
            })
        }


}