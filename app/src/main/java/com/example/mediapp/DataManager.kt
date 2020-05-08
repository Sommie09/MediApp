package com.example.mediapp

import android.database.Cursor
import com.example.mediapp.MediAppDBContract.EmployeeEntry

object DataManager {
     fun fetchAllEmployees(databaseHelper: DatabaseHelper) : ArrayList<Employee>{
         val employees = ArrayList<Employee>()

         val db = databaseHelper.readableDatabase

         val columns = arrayOf(
             EmployeeEntry.COLUMN_ID,
             EmployeeEntry.COLUMN_NAME,
             EmployeeEntry.COLUMN_DOB,
             EmployeeEntry.COLUMN_DESIGNATION
         )

         val cursor : Cursor = db.query(
             EmployeeEntry.TABLE_NAME,
             columns,
             null,null,null,null,null)

         val idPos : Int = cursor.getColumnIndex(EmployeeEntry.COLUMN_ID)
         val namePos : Int = cursor.getColumnIndex(EmployeeEntry.COLUMN_NAME)
         val dobPos : Int = cursor.getColumnIndex(EmployeeEntry.COLUMN_DOB)
         val designationPos : Int = cursor.getColumnIndex(EmployeeEntry.COLUMN_DESIGNATION )

         while(cursor.moveToNext()){
             val id :String = cursor.getString(idPos)
             val name :String = cursor.getString(namePos)
             val dob :Long = cursor.getLong(dobPos)
             val designation :String = cursor.getString(designationPos)

             employees.add(Employee(id,name, dob,designation))
         }

         cursor.close()




         return employees
     }
    
     fun fetchEmployee(databaseHelper: DatabaseHelper, empId : String) : Employee?{
          val db = databaseHelper.readableDatabase
          var employee :Employee? = null

         val columns = arrayOf(
             EmployeeEntry.COLUMN_NAME,
             EmployeeEntry.COLUMN_DOB,
             EmployeeEntry.COLUMN_DESIGNATION
         )

         val selection : String = EmployeeEntry.COLUMN_ID + " LIKE ? "
         val selectionArgs : Array<String> = arrayOf(empId)

         val cursor : Cursor = db.query(
             EmployeeEntry.TABLE_NAME,
             columns,
             selection,
             selectionArgs,
             null,
             null,
             null
         )

         val namePos : Int = cursor.getColumnIndex(EmployeeEntry.COLUMN_NAME)
         val dobPos : Int = cursor.getColumnIndex(EmployeeEntry.COLUMN_DOB)
         val designationPos : Int = cursor.getColumnIndex(EmployeeEntry.COLUMN_DESIGNATION )

         while(cursor.moveToNext()){
             val name :String = cursor.getString(namePos)
             val dob :Long = cursor.getLong(dobPos)
             val designation :String = cursor.getString(designationPos)

             employee = Employee(empId, name, dob, designation)

         }


         cursor.close()
         return employee


     }
}