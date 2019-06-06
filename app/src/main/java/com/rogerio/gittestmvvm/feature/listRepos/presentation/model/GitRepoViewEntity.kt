package com.rogerio.gittestmvvm.feature.listRepos.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GitRepoViewEntity(
    val name: String = "",
    val ownerName: String = "",
    val description: String = "",
    val flag: Boolean = false,
    val loading: Boolean = false,
    val ownerUrl: String = "",
    val repoUrl: String = ""
): Parcelable

