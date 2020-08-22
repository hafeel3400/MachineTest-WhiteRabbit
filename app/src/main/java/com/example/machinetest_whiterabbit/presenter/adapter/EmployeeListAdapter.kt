package com.example.machinetest_whiterabbit.presenter.adapter

import  android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.machinetest_whiterabbit.R
import com.example.machinetest_whiterabbit.presenter.roomdb.employeedatabase.EmployeeDbModel
import com.example.machinetest_whiterabbit.view.mainactivity.EmployeeDetails
import java.util.*
import kotlin.collections.ArrayList


class EmployeeListAdapter(
    var employeeList: List<EmployeeDbModel?>,
    var context: Context
) : RecyclerView.Adapter<EmployeeListAdapter.ViewHolder>(), Filterable {
    var employeeFilterList = ArrayList<EmployeeDbModel>()

    init {
        employeeFilterList = employeeList as ArrayList<EmployeeDbModel>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout_category_list, parent, false)
        return ViewHolder(v)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textEmployeeName.text = employeeFilterList[position]!!.name
        if (employeeFilterList[position]!!.company_name != null) {
            holder.textEmployeeCompanyName.text = employeeFilterList[position]!!.company_name!!
        }
        Glide.with(context).load(employeeFilterList[position]!!.profile_image)
            .placeholder(R.drawable.ic_placeholder_image).into(holder.imageEmployee)
        holder.layoutEmployee.setOnClickListener {
            val i = Intent(context, EmployeeDetails::class.java)
            i.putExtra("name", employeeFilterList[position].name)
            if (employeeFilterList[position].company_name != null) {
                i.putExtra("company_name", employeeFilterList[position].company_name)
            }
            i.putExtra("image", employeeFilterList[position].profile_image)
            i.putExtra("phone", employeeFilterList[position].phone)
            i.putExtra("email", employeeFilterList[position].email)
            i.putExtra("address", employeeFilterList[position].address)
            context.startActivity(i)
        }

    }


    override fun getItemCount(): Int {
        return employeeFilterList.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val textEmployeeName = itemView.findViewById(R.id.rl_employee_name) as TextView
        val imageEmployee = itemView.findViewById(R.id.rl_employee_image) as ImageView
        val layoutEmployee = itemView.findViewById(R.id.lv_employee_layout) as RelativeLayout
        val textEmployeeCompanyName = itemView.findViewById(R.id.rl_company_name) as TextView


    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    employeeFilterList = employeeList as ArrayList<EmployeeDbModel>
                } else {
                    val resultList = ArrayList<EmployeeDbModel>()
                    for (row in employeeList) {
                        if (row?.name?.toLowerCase(Locale.ROOT)
                                ?.contains(charSearch.toLowerCase(Locale.ROOT))!!
                        ) {
                            resultList.add(row!!)
                        }
                    }
                    employeeFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = employeeFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                employeeFilterList = results?.values as ArrayList<EmployeeDbModel>
                notifyDataSetChanged()
            }

        }
    }


}