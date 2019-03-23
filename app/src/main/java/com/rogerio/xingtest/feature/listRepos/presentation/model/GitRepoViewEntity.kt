package com.rogerio.xingtest.feature.listRepos.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GitRepoViewEntity(
    val name: String = ""
): Parcelable

