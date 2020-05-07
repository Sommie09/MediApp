package com.example.mediapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(MediAppDBContract.EmployeeEntry.SQL_CREATE_ENTRIES)
    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(MediAppDBContract.EmployeeEntry.SQL_DROP_TABLE)
        onCreate(db)
    }

    companion object{
        const val DATABASE_NAME = "mediapp.db";
        const val DATABASE_VERSION = 1;
    }
}