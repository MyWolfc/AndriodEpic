package com.example.kardex

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface MateriaKardexDAO {



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    // fun insertAll(vararg users: User) Para inserta mas de uno
    suspend fun insertAll(m: MateriaKardex)

    @Update
    suspend fun update(m:MateriaKardex)

    @Delete
    suspend fun delete(materiakardex: MateriaKardex)
    @Query("SELECT * FROM MateriaKardex WHERE clave_materia = :id")
    suspend fun OnlyOne(id:Int):MateriaKardex?
    @Query("SELECT * FROM MateriaKardex")
    fun getAll() : Flow<List<MateriaKardex>>


}