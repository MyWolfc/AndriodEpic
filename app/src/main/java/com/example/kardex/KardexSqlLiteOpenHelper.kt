package com.example.kardex

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class KardexSqlLiteOpenHelper(contex:Context) :
    SQLiteOpenHelper(contex,"Kardex",null,1)
{
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE MateriaKardex(clave_materia VARCHAR(7) PRIMARY KEY,materia VARCHAR(100),periodo VARCHAR(4),calificacion INTEGER)")

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}