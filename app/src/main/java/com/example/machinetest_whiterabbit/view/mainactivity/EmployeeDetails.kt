package com.example.machinetest_whiterabbit.view.mainactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.machinetest_whiterabbit.R
import kotlinx.android.synthetic.main.activity_employee_details.*


class EmployeeDetails : AppCompatActivity() {

private var name:String?=null
    private var companyName:String?=null
    private var image:String?=null
    private var phone:String?=null
    private var email:String?=null
    private var address:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_details)

        getIntentData()

    }



    private fun getIntentData() {
        if(intent.hasExtra("name")){
            name = intent.getStringExtra("name")
        }
        if(intent.hasExtra("company_name")){
            companyName =intent.getStringExtra("company_name")
        }
        if(intent.hasExtra("image")){
            image =intent.getStringExtra("image")
        }
        if(intent.hasExtra("phone")){
            phone =intent.getStringExtra("phone")
        }
        if(intent.hasExtra("email")){
            email =intent.getStringExtra("email")
        }
        if(intent.hasExtra("address")){
            address =intent.getStringExtra("address")
        }
        item_name.text=name
        item_email.text=email
        item_phone.text=phone
        item_address.text=address
        item_company_name.text=companyName
            Glide.with(this).load(image).placeholder(R.drawable.ic_placeholder_image).into(img_item)


    }


}
