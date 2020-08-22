package com.example.machinetest_whiterabbit.view.mainactivity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.machinetest_whiterabbit.R
import com.example.machinetest_whiterabbit.model.Employee
import com.example.machinetest_whiterabbit.presenter.adapter.EmployeeListAdapter
import com.example.machinetest_whiterabbit.presenter.roomdb.employeedatabase.EmployeeDbModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MainActivityCallBack.view {

    private var mainActivityPresenter: MainActivityPresenter? = null
    var adapter:EmployeeListAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainActivityPresenter = MainActivityPresenter(this)
        mainActivityPresenter!!.fetchListFromDb(this)

        searchfunct()

    }

    private fun searchfunct() {

            search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
                android.widget.SearchView.OnQueryTextListener {

                override fun onQueryTextChange(newText: String): Boolean {

                    return false
                }

                override fun onQueryTextSubmit(query: String): Boolean {
                    adapter!!.filter.filter(query)
                    return false
                }

            })
        search_view.setOnCloseListener {
            mainActivityPresenter!!.fetchListFromDb(applicationContext)
            false
        }

    }

    override fun onSuccess(employeeList: List<Employee>) {
        mainActivityPresenter?.saveEmployeeListtoDatabase(employeeList, applicationContext)

//       setUpRecyclerView(employeeList)
    }


    override fun onError(errorMsg: String) {
        Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()
    }

    private fun setUpRecyclerView(employeeList: List<EmployeeDbModel?>) {
        recyc_category.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        adapter = EmployeeListAdapter(employeeList, this)
        recyc_category.adapter = adapter
    }

    override fun displayProgres() {

    }

    override fun dismissProgress() {

    }

    override fun fetchListSuccessFromDb(employeeList: List<EmployeeDbModel?>) {
        if(employeeList.isNotEmpty()){
            setUpRecyclerView(employeeList)
        }else{
            mainActivityPresenter!!.fetchEmployeeList()
        }

    }


}
