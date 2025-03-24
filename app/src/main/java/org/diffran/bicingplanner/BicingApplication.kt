package org.diffran.bicingplanner

import android.app.Application
import org.diffran.bicingplanner.data.AppRepository
import org.diffran.bicingplanner.data.AppRepositoryImpl
import org.diffran.bicingplanner.data.network.RetrofitServiceFactory

class BicingApplication : Application() {
    lateinit var repository: AppRepository

    override fun onCreate(){
        super.onCreate()

        val retrofitService = RetrofitServiceFactory.makeRetrofitService()
        repository = AppRepositoryImpl(retrofitService)
    }
}