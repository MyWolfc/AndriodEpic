package com.example.kardex

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MateriaKardex(

    @PrimaryKey @ColumnInfo(name = "clave_materia") val claveMateria : String,
    var periodo : String,
    var materia : String,
    var calificacion : Int
)
