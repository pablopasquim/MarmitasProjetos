package com.example.marmitasapp.ViewModel

import PedidoViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.marmitasapp.Model.Data.DAO.PedidoDAO

class PedidoViewModelFactory(private val pedidoDao: PedidoDAO) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PedidoViewModel::class.java)) {
            return PedidoViewModel(pedidoDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
