package com.example.machinetest_whiterabbit.view.mainactivity

import android.content.Context
import com.example.machinetest_whiterabbit.model.Employee
import com.example.machinetest_whiterabbit.presenter.roomdb.employeedatabase.EmployeeDbModel

interface MainActivityCallBack {
    interface view{
        fun onSuccess(employeeList: List<Employee>)
        fun onError(errorMsg:String)
        fun displayProgres()
        fun dismissProgress()
        fun fetchListSuccessFromDb(employeeList: List<EmployeeDbModel?>)
    }

    interface presenter{
        fun fetchEmployeeList()
        fun saveEmployeeListtoDatabase(
            employeeList: List<Employee>,
            applicationContext: Context
        )
        fun fetchListFromDb(context: Context)
    }
}