package com.example.machinetest_whiterabbit.model

import com.google.gson.annotations.SerializedName

class Company {
    @SerializedName("bs")
    var mBs: String? = null

    @SerializedName("catchPhrase")
    var mCatchPhrase: String? = null

    @SerializedName("name")
    var mName: String? = null
}