package org.diffran.bicingplanner.data

interface AppRepository{
    suspend fun getBicingPred(type: String, time: Int) : String
}

class AppRepositoryImpl(private val retrofitService: RetrofitService)  : AppRepository{
    override suspend fun getBicingPred(type: String, time: Int) : String {
        return retrofitService.getGeoJsonFromApi(type,time).string()
    }
}
