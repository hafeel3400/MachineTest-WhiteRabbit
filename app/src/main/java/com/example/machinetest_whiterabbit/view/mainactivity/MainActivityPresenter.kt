package com.example.machinetest_whiterabbit.view.mainactivity

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast
import com.example.machinetest_whiterabbit.ApiService
import com.example.machinetest_whiterabbit.Constants
import com.example.machinetest_whiterabbit.model.Employee
import com.example.machinetest_whiterabbit.presenter.roomdb.DatabaseClient
import com.example.machinetest_whiterabbit.presenter.roomdb.employeedatabase.EmployeeDbModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivityPresenter(var view: MainActivityCallBack.view) : MainActivityCallBack.presenter {
    override fun fetchEmployeeList() {
        view.displayProgres()
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(ApiService::class.java)
        val call = service.getEmployeeList()
        call.enqueue(object : Callback<List<Employee>> {
            override fun onResponse(
                call: Call<List<Employee>>,
                response: Response<List<Employee>>
            ) {
                view.dismissProgress()
                if (response.code() == 200) {
                    val employeeResponse = response.body()!!
                    view.onSuccess(employeeResponse)
                } else {
                    view.onError(response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<List<Employee>>, t: Throwable) {
                view.dismissProgress()
                view.onError(t.message!!)
            }
        })

    }

    override fun saveEmployeeListtoDatabase(
        employeeList: List<Employee>,
        applicationContext: Context
    ) {
        val st =
            SaveEmployeeList(employeeList, applicationContext,view)
        st.execute()
    }

    override fun fetchListFromDb(context: Context) {
        val st =
            GetEmployeeList(context, view)
        st.execute()
    }


    private class SaveEmployeeList internal constructor(
        var employeeList: List<Employee>,
        var context: Context,
        var view: MainActivityCallBack.view
    ) :
        AsyncTask<Void?, Void?, Void?>() {


        override fun onPostExecute(aVoid: Void?) {
            super.onPostExecute(aVoid)
            Log.d("saved data to database", "success")
            val st =
                GetEmployeeList(context, view)
            st.execute()
        }

        override fun doInBackground(vararg params: Void?): Void? {
            //creating a task

            for (i in employeeList.indices) {
                val employee: Employee =
                    employeeList[i]
                val employeeDbModel = EmployeeDbModel()
                employeeDbModel.name = employee.mName
                if (employee.mCompany != null) {
                    employeeDbModel.company_name = employee.mCompany?.mName
                }
                employeeDbModel.profile_image = employee.mProfileImage
                employeeDbModel.email=employee.mEmail
                employeeDbModel.phone=employee.mPhone
                employeeDbModel.address= employee.mAddress?.mStreet
                //adding to database
                DatabaseClient.getInstance(context)!!.employeeDatabase
                    .employeeDao()!!.insert(employeeDbModel)
            }
            return null
        }


    }


    private class GetEmployeeList internal constructor(
        var context: Context,
        var employeeView: MainActivityCallBack.view
    ) :
        AsyncTask<Void?, Void?, List<EmployeeDbModel?>>() {


        override fun onPostExecute(employeeList: List<EmployeeDbModel?>) {
            super.onPostExecute(employeeList)
            employeeView.fetchListSuccessFromDb(employeeList)
            Toast.makeText(context, "success db", Toast.LENGTH_LONG).show()
//                availabilityView.onUpdateAvailabilityWhenInternet(availabilitiesDBModelList)

        }

        override fun doInBackground(vararg params: Void?): List<EmployeeDbModel?>? {
            return DatabaseClient
                .getInstance(context)
                ?.employeeDatabase
                ?.employeeDao()
                ?.all
        }


    }


}