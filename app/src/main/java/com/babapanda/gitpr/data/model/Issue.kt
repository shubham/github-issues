package com.babapanda.gitpr.data.model

import com.google.gson.annotations.SerializedName

data class Issue(
    @SerializedName("href") var href: String? = null
)