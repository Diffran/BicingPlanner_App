package org.diffran.bicingplanner.data

import okhttp3.ResponseBody

interface AppRepository{
    suspend fun getBicingPred() : ResponseBody
}

class AppRepositoryImpl(private val retrofitService: RetrofitService)  : AppRepository{
    override suspend fun getBicingPred() : ResponseBody {
        return retrofitService.getGeoJsonFromApi()
    }
}
