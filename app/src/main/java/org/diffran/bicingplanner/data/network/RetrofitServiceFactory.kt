package org.diffran.bicingplanner.data.network

import org.diffran.bicingplanner.data.RetrofitService
import retrofit2.Retrofit

object RetrofitServiceFactory {
    fun makeRetrofitService() : RetrofitService{
        return Retrofit.Builder()
            .baseUrl("")
            .build()
            .create(RetrofitService::class.java)
    }
}