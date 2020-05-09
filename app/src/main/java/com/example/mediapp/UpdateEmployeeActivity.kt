package com.example.mediapp

import android.app.DatePickerDialog

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_employee.*
import java.text.SimpleDateFormat
import java.util.*

class UpdateEmployeeActivity : AppCompatActivity() {
    lateinit var databaseHelper: DatabaseHelper
    val myCalendar = Calendar.getInstance()

    var empId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_employee)

        databaseHelper = DatabaseHelper(this)

        val bundle= intent.extras

        bundle?.let {
            empId = bundle.getString(MediAppDBContract.EmployeeEntry.COLUMN_ID)

            val employee = DataManager.fetchEmployee(databaseHelper, empId!!)

            employee?.let{
                etEmpName.setText(employee.name)
                etDesignation.setText(employee.designation)
                etDOB.setText(getFormattedDate(employee.dob))
            }
        }



        val date = DatePickerDialog.OnDateSetListener { view, year, monthOfDay, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, monthOfDay)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            etDOB.setText(getFormattedDate(myCalendar.timeInMillis ))
        }
        etDOB.setOnClickListener {
            setUpCalendarDate(date)
        }

        bSave.setOnClickListener {
            saveEmployee()
        }

        bCancel.setOnClickListener {
            finish()
        }

    }

    private fun setUpCalendarDate(date: DatePickerDialog.OnDateSetListener) {
        DatePickerDialog(
             this, date, myCalendar.get(Calendar.YEAR),
            myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun saveEmployee(){
        var isValid = true

        etEmpName.error = if(etEmpName?.text.toString().isEmpty()){
            isValid = false
            "Required Field"
        } else null

        etDesignation.error = if(etDesignation?.text.toString().isEmpty()){
            isValid = false
            "Required Field"
        } else null

        if(isValid){

        }
    }

    private fun getFormattedDate(dobInMilis: Long?) : String {
        return dobInMilis?.let{
            val sdf = SimpleDateFormat("d MMM, yyyy", Locale.getDefault())
            sdf.format(dobInMilis)
        } ?: "NotFound"
    }
}