package com.rogerio.xingtest.services

import com.rogerio.xingtest.services.models.GitRepo
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface IGitService {
    @GET("users/xing/repos")
    fun getRepos(@Query("page") page: Int,
                 @Query("per_page") size: Int): Single<List<GitRepo>>
}