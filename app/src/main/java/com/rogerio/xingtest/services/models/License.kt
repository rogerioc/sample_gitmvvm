package com.rogerio.xingtest.services.models

import com.squareup.moshi.Json

data class License(@Json(name = "name")
                   val name: String = "",
                   @Json(name = "spdx_id")
                   val spdxId: String = "",
                   @Json(name = "key")
                   val key: String = "",
                   @Json(name = "url")
                   val url: String = "",
                   @Json(name = "node_id")
                   val nodeId: String = "")