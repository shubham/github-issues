package com.babapanda.gitpr.data.model

import com.google.gson.annotations.SerializedName

data class Head(
    @SerializedName("label") var label: String? = null,
    @SerializedName("ref") var ref: String? = null,
    @SerializedName("sha") var sha: String? = null,
    @SerializedName("user") var user: UserDTO? = UserDTO(),
    @SerializedName("repo") var repo: Repo? = Repo()
)