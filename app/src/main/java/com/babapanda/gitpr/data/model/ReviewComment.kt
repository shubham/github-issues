package com.babapanda.gitpr.data.model

import com.google.gson.annotations.SerializedName

data class ReviewComment(
    @SerializedName("href") var href: String? = null
)