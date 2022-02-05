package app.cadere.ui.common.network.service

import app.cadere.ui.common.network.model.AvailableLocations

interface LocationApi {
    suspend fun getJsonFromApi(): AvailableLocations
}