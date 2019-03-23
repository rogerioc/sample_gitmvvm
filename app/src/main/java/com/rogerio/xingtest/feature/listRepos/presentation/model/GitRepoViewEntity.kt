package com.rogerio.xingtest.feature.listRepos.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GitRepoViewEntity(
    val name: String = "",
    val ownerName: String = "",
    val desciption: String = "",
    val flag: Boolean = false,
    val loading: Boolean = false
): Parcelable

