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

    // Função para inserir um novo cliente
    fun inserirCliente(nome: String, email: String, endereco: String, preferencias: String): String {
        if (nome.isBlank() || email.isBlank()) {
            return "Preencha todos os campos!" // Mensagem de erro caso os campos estejam vazios
        }

        // Cria um novo cliente com o id = 0 (o Room vai gerar o id automaticamente)
        val cliente = Cliente(id = 0, nome = nome, email = email, endereco = endereco, preferencias = preferencias)

        // Lança uma coroutine para inserir o cliente no banco de dados
        viewModelScope.launch {
            clienteDao.inserir(cliente) // O Room vai gerar automaticamente o ID
            carregarClientes() // Carrega novamente a lista de clientes após inserção
            resultadoMensagem.value = "Cliente salvo com sucesso!" // Mensagem de sucesso
        }

        return "Cliente salvo com sucesso!"
    }

    // Função para excluir um cliente
    fun excluirCliente(cliente: Cliente) {
        viewModelScope.launch {
            clienteDao.deletar(cliente)
            carregarClientes()
            resultadoMensagem.value = "Cliente excluído com sucesso!" // Mensagem de sucesso
        }
    }

    // Função para atualizar um cliente
    fun atualizarCliente(id: Int, nome: String, email: String, endereco: String, preferencias: String): String {
        if (nome.isBlank() || email.isBlank()) {
            resultadoMensagem.value = "Preencha todos os campos!" // Mensagem de erro caso os campos estejam vazios
            return ""
        }

        viewModelScope.launch {
            val clienteExistente = clienteDao.buscarPorId(id) // Busca o cliente pelo id no banco
            if (clienteExistente != null) {
                val clienteAtualizado = clienteExistente.copy(
                    nome = nome,
                    email = email,
                    endereco = endereco,
                    preferencias = preferencias
                )
                clienteDao.atualizar(clienteAtualizado) // Atualiza o cliente no banco
                carregarClientes() // Recarrega a lista de clientes após atualização
                resultadoMensagem.value = "Cliente atualizado com sucesso!" // Mensagem de sucesso
            } else {
                resultadoMensagem.value = "Erro ao atualizar cliente: Cliente não encontrado!" // Mensagem de erro
            }
        }

        return "Cliente atualizado com sucesso!"
    }
}