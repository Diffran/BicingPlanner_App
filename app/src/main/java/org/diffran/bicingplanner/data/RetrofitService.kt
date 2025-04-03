package org.diffran.bicingplanner.data

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("bicing_data")
    suspend fun getGeoJsonFromApi(@Query("type") type: String,
                                  @Query("time") time: Int) : ResponseBody
}
