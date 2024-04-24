package com.example.kardex

import androidx.room.Room

object Singleton {
    val kardex = mutableListOf<MateriaKardex>()

//    val db = Room.databaseBuilder(
//        applicationContext,
//        AppDatabase::class.java, "MateriaKardex"
//    ).build()

    fun getItem(materialKardex:MateriaKardex): Int{
        for ((index, item) in kardex.withIndex()) {
            if (item == materialKardex) {
                return index
            }
        }
        return -1 // Si no se encuentra el objeto, retornamos -1
    }

    fun removeItem(position: Int) {
        kardex.removeAt(position)
    }
}