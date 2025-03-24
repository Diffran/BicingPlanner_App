package org.diffran.bicingplanner.data

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Header

interface RetrofitService {
    @GET("bicing_geojson")
    suspend fun getGeoJsonFromApi() : ResponseBody
}
