package com.example.kardex
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import java.util.concurrent.Flow

class FisrtsFragmentViewModel(private val repository : MaterialKardexRepository): ViewModel() {

    val materiaKardexTodasMaterias : LiveData<List<MateriaKardex>> = repository.allMK.asLiveData()
}

class FirstFragmentViewModelFactory(private val repository: MaterialKardexRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FisrtsFragmentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FisrtsFragmentViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}