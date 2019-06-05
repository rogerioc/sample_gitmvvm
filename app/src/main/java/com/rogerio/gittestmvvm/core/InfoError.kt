package com.rogerio.xingtest.core

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class InfoError(
    val error: Int = 0
): Parcelable

