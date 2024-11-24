package com.example.marmitasapp.ViewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marmitasapp.Model.Data.DAO.ClienteDAO
import com.example.marmitasapp.Model.Entity.Cliente
import kotlinx.coroutines.launch

class ClienteViewModel(private val clienteDao: ClienteDAO) : ViewModel() {

    var listaClientes = mutableStateOf(listOf<Cliente>())
        private set

    var resultadoMensagem = mutableStateOf("") // Variável para armazenar a mensagem de resultado

    init {
        carregarClientes()
    }

    private fun carregarClientes() {
        viewModelScope.launch {
            listaClientes.value = clienteDao.listarTodos() // Carrega todos os clientes
        }
    }


    fun inserirCliente(nome: String, email: String, endereco: String, preferencias: String): String {
        if (nome.isBlank() || email.isBlank()) {
            return "Preencha todos os campos!"
        }


        val cliente = Cliente(id = 0, nome = nome, email = email, endereco = endereco, preferencias = preferencias)


        viewModelScope.launch {
            clienteDao.inserir(cliente)
            carregarClientes()
            resultadoMensagem.value = "Cliente salvo com sucesso!"
        }

        return "Cliente salvo com sucesso!"
    }


    fun excluirCliente(cliente: Cliente) {
        viewModelScope.launch {
            clienteDao.deletar(cliente)
            carregarClientes()
            resultadoMensagem.value = "Cliente excluído com sucesso!"
        }
    }

    fun atualizarCliente(id: Int, nome: String, email: String, endereco: String, preferencias: String) {
        if (nome.isBlank() || email.isBlank()) {
            resultadoMensagem.value = "Preencha todos os campos!"
            return
        }

        viewModelScope.launch {
            val clienteExistente = clienteDao.buscarPorId(id)
            if (clienteExistente != null) {
                val clienteAtualizado = clienteExistente.copy(
                    nome = nome,
                    email = email,
                    endereco = endereco,
                    preferencias = preferencias
                )
                clienteDao.atualizar(clienteAtualizado)
                carregarClientes() // Recarrega a lista após a atualização
                resultadoMensagem.value = "Cliente atualizado com sucesso!"
            } else {
                resultadoMensagem.value = "Erro ao atualizar cliente: Cliente não encontrado!"
            }
        }
    }

    fun buscarClientePorId(id: Int): Cliente? {
        var cliente: Cliente? = null
        viewModelScope.launch {
            cliente = clienteDao.buscarPorId(id)
        }
        return cliente
    }
}