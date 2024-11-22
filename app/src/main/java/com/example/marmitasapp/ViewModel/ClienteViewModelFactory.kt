package com.example.marmitasapp.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.marmitasapp.Model.Data.DAO.ClienteDAO

class ClienteViewModelFactory(private val clienteDao: ClienteDAO) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ClienteViewModel::class.java)) {
            return ClienteViewModel(clienteDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
