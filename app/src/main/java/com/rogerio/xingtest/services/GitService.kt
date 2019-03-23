package com.rogerio.xingtest.services

import com.rogerio.xingtest.BuildConfig
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class GitService {
    private val baseUrl: String = BuildConfig.BASE_URL
    private var service: IGitService

    init {
        service = Retrofit.Builder().baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(MoshiConverterFactory.create())
            .client(OkHttpClient())
            .build()
            .create(IGitService::class.java)
    }

    fun getService(): IGitService {
        return service
    }

}