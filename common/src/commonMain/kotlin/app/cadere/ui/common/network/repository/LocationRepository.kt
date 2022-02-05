package app.cadere.ui.common.network.repository

import app.cadere.ui.common.network.model.LocationResponse
import app.cadere.ui.common.network.service.LocationApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LocationRepository: KoinComponent {

    private val api: LocationApi by inject()

    fun getLocations(): Flow<List<LocationResponse>> {
        return flow {
            val locations = api.getJsonFromApi()
            println("Hello my locations are ${locations.rooms}")
            emit(locations.rooms)
        }.flowOn(Dispatchers.Default)
    }

}