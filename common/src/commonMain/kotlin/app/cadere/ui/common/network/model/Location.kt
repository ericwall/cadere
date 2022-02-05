package app.cadere.ui.common.network.model

import kotlinx.serialization.Serializable


@Serializable
data class AvailableLocations(
    @Serializable val rooms: List<LocationResponse>
)

@Serializable
data class LocationResponse(
    val name: String,
    val spots: Int,
    val thumbnail: String
)

@Serializable
data class Location(
    val name: String,
    val spots: Int,
    val imageUrl: String
)

fun LocationResponse.toLocation() = Location(this.name, this.spots, this.thumbnail)
