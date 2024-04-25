package com.example.kardex

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SecondFragmentViewModel(private val repository : MaterialKardexRepository): ViewModel() {


//    private val _registroGuardado= MutableLiveData(false)
//    val registroGuardado : LiveData<Boolean>
//        get() {
//            return _registroGuardado
//        }
    fun insertarMaterialKardex(materiaKardex: MateriaKardex) = viewModelScope.launch{
        repository.insert(materiaKardex)

    }
    fun conseguirUno(id: Int) : LiveData<MateriaKardex?>
    {
        return liveData {
            emit(repository.getOneById(id))
        }
    }
    fun matarRegistro(mk:MateriaKardex) = viewModelScope.launch {
        repository.elimanrDB(mk)
    }

    fun actualizar(mk:MateriaKardex) =viewModelScope.launch {
        repository.modificarUno(mk)
    }




        //Guardar en la base de datos
        //_registroGuardado.value = true

}

class SecondFragmentViewModelFactory(private val repository: MaterialKardexRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SecondFragmentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SecondFragmentViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}