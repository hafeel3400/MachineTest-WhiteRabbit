package com.example.machinetest_whiterabbit.presenter.roomdb.employeedatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class EmployeeDbModel : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    @ColumnInfo(name = "name")
    var name: String? = null

    @ColumnInfo(name = "profile_image")
    var profile_image: String? = null

    @ColumnInfo(name = "company_name")
    var company_name: String? = null

    @ColumnInfo(name = "email")
    var email: String? = null

    @ColumnInfo(name = "phone")
    var phone: String? = null

    @ColumnInfo(name = "address")
    var address: String? = null


}