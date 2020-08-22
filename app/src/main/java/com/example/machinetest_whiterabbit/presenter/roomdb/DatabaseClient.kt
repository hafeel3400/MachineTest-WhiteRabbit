package com.example.machinetest_whiterabbit.presenter.roomdb

import android.content.Context
import androidx.room.Room
import com.example.machinetest_whiterabbit.presenter.roomdb.employeedatabase.EmployeeDatabase

class DatabaseClient private constructor(private val mCtx: Context) {

    //our app database object
    private val appDatabase: EmployeeDatabase
    val employeeDatabase: EmployeeDatabase
        get() = appDatabase

    companion object {
        private var mInstance: DatabaseClient? = null

        @Synchronized
        fun getInstance(mCtx: Context): DatabaseClient? {
            if (mInstance == null) {
                mInstance = DatabaseClient(mCtx)
            }
            return mInstance
        }
    }

    init {

        //creating the app database with Room database builder
        //Availability is the name of the database
        appDatabase = Room.databaseBuilder<EmployeeDatabase>(
            mCtx,
            EmployeeDatabase::class.java,
            "Employee"
        ).build()
    }
}