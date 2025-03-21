package org.diffran.bicingplanner.data

import retrofit2.http.GET

interface RetrofitService {
    @GET("/api/bicing_geojson")
    suspend fun getGeoJsonFromApi() : String
}
