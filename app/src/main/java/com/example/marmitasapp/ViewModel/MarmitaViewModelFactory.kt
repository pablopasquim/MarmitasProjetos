package com.example.marmitasapp.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.marmitasapp.Model.Data.DAO.MarmitaDAO

class MarmitaViewModelFactory(private val marmitaDao: MarmitaDAO) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MarmitaViewModel::class.java)) {
            return MarmitaViewModel(marmitaDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
