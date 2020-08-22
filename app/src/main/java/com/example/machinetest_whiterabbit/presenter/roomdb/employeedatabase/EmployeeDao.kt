package com.example.machinetest_whiterabbit.presenter.roomdb.employeedatabase

import androidx.room.*

@Dao
interface EmployeeDao {
    @get:Query("SELECT * FROM EmployeeDbModel")
    val all: List<EmployeeDbModel?>?

    @Insert
    fun insert(task: EmployeeDbModel?)

    @Delete
    fun delete(task: EmployeeDbModel?)

    @Update
    fun update(task: EmployeeDbModel?)

    @Query("DELETE FROM EmployeeDbModel")
    fun deleteAll()
}