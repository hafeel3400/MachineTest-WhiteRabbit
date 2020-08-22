package com.example.machinetest_whiterabbit.model

import com.google.gson.annotations.SerializedName

class Employee {
    @SerializedName("address")
    var mAddress: Address? = null

    @SerializedName("company")
    var mCompany: Company? = null

    @SerializedName("email")
    var mEmail: String? = null

    @SerializedName("id")
    var mId: Long? = null

    @SerializedName("name")
    var mName: String? = null

    @SerializedName("phone")
    var mPhone: String? = null

    @SerializedName("profile_image")
    var mProfileImage: String? = null

    @SerializedName("username")
    var mUsername: String? = null

    @SerializedName("website")
    var mWebsite: String? = null
}