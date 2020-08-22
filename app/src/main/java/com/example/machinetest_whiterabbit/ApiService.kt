package com.example.machinetest_whiterabbit


import com.example.machinetest_whiterabbit.model.Employee
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("v2/5d565297300000680030a986")
    fun getEmployeeList(): Call<List<Employee>>

}