package com.example.mediapp

import android.content.ContentValues
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
             EmployeeEntry.COLUMN_DESIGNATION,
             EmployeeEntry.COLUMN_SURGEON

         )

         val cursor : Cursor = db.query(
             EmployeeEntry.TABLE_NAME,
             columns,
             null,null,null,null,null)

         val idPos : Int = cursor.getColumnIndex(EmployeeEntry.COLUMN_ID)
         val namePos : Int = cursor.getColumnIndex(EmployeeEntry.COLUMN_NAME)
         val dobPos : Int = cursor.getColumnIndex(EmployeeEntry.COLUMN_DOB)
         val designationPos : Int = cursor.getColumnIndex(EmployeeEntry.COLUMN_DESIGNATION )
         val surgeonPos = cursor.getColumnIndex(EmployeeEntry.COLUMN_SURGEON)

         while(cursor.moveToNext()){
             val id :String = cursor.getString(idPos)
             val name :String = cursor.getString(namePos)
             val dob :Long = cursor.getLong(dobPos)
             val designation :String = cursor.getString(designationPos)
             val surgeon = cursor.getInt(surgeonPos)

             employees.add(Employee(id,name, dob,designation, surgeon))
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
         val surgeonPos = cursor.getColumnIndex(EmployeeEntry.COLUMN_SURGEON)

         while(cursor.moveToNext()){
             val name :String = cursor.getString(namePos)
             val dob :Long = cursor.getLong(dobPos)
             val designation :String = cursor.getString(designationPos)
             val surgeon = cursor.getInt(surgeonPos)



             employee = Employee(empId, name, dob, designation, surgeonPos )

         }


         cursor.close()
         return employee


     }

     fun updateEmployee(databaseHelper: DatabaseHelper, employee: Employee) {
         val db = databaseHelper.writableDatabase

         val values = ContentValues()
         values.put(EmployeeEntry.COLUMN_NAME, employee.name)
         values.put(EmployeeEntry.COLUMN_DESIGNATION, employee.designation)
         values.put(EmployeeEntry.COLUMN_DOB, employee.dob)
         values.put(EmployeeEntry.COLUMN_SURGEON, employee.isSurgeon)

         val selection = EmployeeEntry.COLUMN_ID + " LIKE ? "
         val selectionArgs = arrayOf(employee.id)

         db.update(EmployeeEntry.TABLE_NAME, values, selection, selectionArgs)
     }

    fun deleteEmployee(databaseHelper: DatabaseHelper, empId: String): Int{
        val db = databaseHelper.writableDatabase

        val selection = EmployeeEntry.COLUMN_ID + " LIKE ? "
        val selectionArgs = arrayOf(empId)

        return db.delete(EmployeeEntry.TABLE_NAME, selection, selectionArgs)
     }

    fun deleteAllEmployee(databaseHelper: DatabaseHelper): Int {
         val db = databaseHelper.writableDatabase
        return db.delete(EmployeeEntry.TABLE_NAME, "1", null)

    }
}