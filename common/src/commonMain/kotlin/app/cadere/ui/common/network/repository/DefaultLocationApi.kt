package app.cadere.ui.common.network.repository

import app.cadere.ui.common.network.model.AvailableLocations
import app.cadere.ui.common.network.service.LocationApi
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class DefaultLocationApi(engine: HttpClientEngine) : LocationApi {

    private val client = HttpClient(engine) {
        install(ContentNegotiation) {

            json(Json {
                prettyPrint = true
                isLenient = true
            })

        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.BODY
        }
        install(HttpTimeout) {
            val timeout = 30000L
            connectTimeoutMillis = timeout
            requestTimeoutMillis = timeout
            socketTimeoutMillis = timeout
        }
    }

    override suspend fun getJsonFromApi(): AvailableLocations {
        return client.get {
            rooms("/rooms.json")
        }.body()
    }

    private fun HttpRequestBuilder.rooms(path: String) {
        url {
            takeFrom("https://wetransfer.github.io")
            encodedPath = path
        }
    }
}