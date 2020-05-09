package com.example.mediapp

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*

class EmployeeListAdapter(private val context: Context) : RecyclerView.Adapter<EmployeeListAdapter.EmployeeViewHolder>() {

    lateinit var employeeList :ArrayList  <Employee>

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int): EmployeeViewHolder {

        val itemView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        return EmployeeViewHolder(itemView)
    }

    override fun getItemCount(): Int = employeeList.size

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
       val employee : Employee = employeeList[position]
        holder.setData(employee.name, employee.designation, employee.dob, position)
        holder.setListener(context, employeeList )
    }

    fun setEmployees(employees: ArrayList<Employee>) {
            employeeList = employees
            notifyDataSetChanged()
    }

    class EmployeeViewHolder(itemView: View)  : RecyclerView.ViewHolder(itemView) {

        var pos = 0

        fun setData(name: String, designation: String, dob : Long, pos : Int) {
           itemView.tvEmpName.text= name
            itemView.tvEmpDesignation.text = designation
            itemView.tvDateOfBirth.text = dob .toString()
            this.pos = pos
        }

        fun setListener(context: Context, employees: ArrayList<Employee> ) {
            itemView.setOnClickListener{
                val intent = Intent(context, UpdateEmployeeActivity::class.java)
                intent.putExtra(MediAppDBContract.EmployeeEntry.COLUMN_ID, employees[pos].id)
                (context as Activity) .startActivityForResult(intent, 2)

            }
        }

    }
}