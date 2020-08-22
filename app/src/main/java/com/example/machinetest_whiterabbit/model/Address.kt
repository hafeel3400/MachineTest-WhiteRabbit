package com.example.machinetest_whiterabbit.model

import com.google.gson.annotations.SerializedName

class Address {
    @SerializedName("city")
    var mCity: String? = null

    @SerializedName("geo")
    var mGeo: Geo? = null

    @SerializedName("street")
    var mStreet: String? = null

    @SerializedName("suite")
    var mSuite: String? = null

    @SerializedName("zipcode")
    var mZipcode: String? = null
}