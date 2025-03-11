package org.diffran.bicingplanner.model


data class BicingStationData(
    val last_updated: Long,
    val ttl: Int,
    val data: BicingData
)

data class BicingData(
    val stations: List<BicingStation>
)

data class BicingStation(
    val station_id: Int,
    val name: String,
    val physical_configuration: String,
    val lat: Double,
    val lon: Double,
    val altitude: Double,
    val address: String,
    val cross_street: String,
    val post_code: String,
    val capacity: Int,
    val is_charging_station: Boolean,
    val short_name: Int,
    val nearby_distance: Double,
    val _ride_code_support: Boolean,
    val rental_uris: Any?
)
