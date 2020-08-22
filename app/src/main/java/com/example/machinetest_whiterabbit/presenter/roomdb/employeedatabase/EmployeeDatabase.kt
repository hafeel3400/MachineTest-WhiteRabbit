package com.example.machinetest_whiterabbit.presenter.roomdb.employeedatabase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [EmployeeDbModel::class], version = 1)
abstract class EmployeeDatabase : RoomDatabase() {
    abstract fun employeeDao(): EmployeeDao?
}