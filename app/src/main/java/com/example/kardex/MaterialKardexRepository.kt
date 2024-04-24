package com.example.kardex

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class MaterialKardexRepository(private val MKDAO: MateriaKardexDAO) {
    //este tipo de dato flow observa cada registro y se actualiza nb tiempo real
    val allMK: Flow<List<MateriaKardex>> = MKDAO.getAll()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(mk: MateriaKardex) {
        MKDAO.insertAll(mk)
    }
}