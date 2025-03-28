package org.diffran.bicingplanner.data

import okhttp3.ResponseBody

interface AppRepository{
    suspend fun getBicingPred() : String
}

class AppRepositoryImpl(private val retrofitService: RetrofitService)  : AppRepository{
    override suspend fun getBicingPred() : String {
        return retrofitService.getGeoJsonFromApi().string()
    }
}
