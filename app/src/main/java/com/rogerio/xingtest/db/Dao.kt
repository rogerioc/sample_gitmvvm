package com.rogerio.xingtest.db

import androidx.paging.DataSource
import androidx.room.*
import io.reactivex.Single


@Dao
interface RepoDao {
    @Query("SELECT * FROM GitRepoEntity")
    fun getAll(): Single<List<GitRepoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insertAll(gitRepoEntities: List<GitRepoEntity>)

    @Delete
    fun delete(GitRepoEntity: GitRepoEntity)

    @Query("SELECT * FROM GitRepoEntity")
    fun  selectPaged(): DataSource.Factory<Int, GitRepoEntity>

    @Query("SELECT * FROM GitRepoEntity LIMIT :size OFFSET :page")
    fun get(page: Int, size: Int): Single<List<GitRepoEntity>>


}