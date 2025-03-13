package org.diffran.bicingplanner.model

data class BicingState(
    val type: String,
    val stations: List<BicingStationState>
)


data class BicingStationState(
    val station_id: Int,
    val time_slots: List<TimeSlotData>
)

data class TimeSlotData(
    val time_slot: String,
    val percentagesHighest: String
)