package com.rogerio.gittestmvvm.services

import com.rogerio.gittestmvvm.services.models.GitRepo
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface IGitService {
    @GET("users/xing/repos")
    fun getRepos(@Query("page") page: Int,
                 @Query("per_page") size: Int): Single<List<GitRepo>>
}