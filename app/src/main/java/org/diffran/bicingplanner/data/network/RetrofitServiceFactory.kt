package org.diffran.bicingplanner.data.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.diffran.bicingplanner.data.RetrofitService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitServiceFactory {
    private const val API_KEY = "1234"

    fun makeRetrofitService(): RetrofitService {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val originalRequest = chain.request()

                val authenticatedRequest = originalRequest.newBuilder()
                    .addHeader("Authentication", API_KEY)
                    .build()

                chain.proceed(authenticatedRequest)
            }
            .build()

        return Retrofit.Builder()
            .baseUrl("http://10.7.13.64:8080/api/")
            //.baseUrl("http://10.0.2.2:8080/api/") nomes el emulador
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitService::class.java)
    }
}
