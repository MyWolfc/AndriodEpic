package com.example.kardex

import android.app.Application

class MaterialKardexApplication: Application() {

    val database by lazy {AppDatabase.getDatabase(this)}
    val repository by lazy { MaterialKardexRepository(database.MateriaKardexDAO()) }

}