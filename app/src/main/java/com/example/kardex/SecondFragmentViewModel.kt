package com.example.kardex

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
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