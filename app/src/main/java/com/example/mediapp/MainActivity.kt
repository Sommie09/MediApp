package com.example.mediapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var databaseHelper: DatabaseHelper
    private val employeeListAdapter= EmployeeListAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        databaseHelper = DatabaseHelper(this)

        recyclerView.adapter = employeeListAdapter
        recyclerView.layoutManager= LinearLayoutManager(this)
        employeeListAdapter.setEmployees(DataManager.fetchAllEmployees(databaseHelper))


        fab.setOnClickListener {
            val addEmployeeActivity = Intent(this, AddEmployeeActivity::class.java)
            startActivityForResult(addEmployeeActivity, 1);
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK){
            employeeListAdapter.setEmployees(DataManager.fetchAllEmployees(databaseHelper))
        }
    }
}
