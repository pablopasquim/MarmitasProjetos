package com.example.marmitasapp.ViewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marmitasapp.Model.Data.DAO.MarmitaDAO
import com.example.marmitasapp.Model.Entity.Marmita
import kotlinx.coroutines.launch

class MarmitaViewModel(private val marmitaDao: MarmitaDAO) : ViewModel() {

    var listaMarmitas = mutableStateOf(listOf<Marmita>())
        private set

    var resultadoMensagem = mutableStateOf("") // Variável para armazenar a mensagem de resultado

    init {
        carregarMarmitas()
    }

    private fun carregarMarmitas() {
        viewModelScope.launch {
            listaMarmitas.value = marmitaDao.listarTodas()
        }
    }


    fun salvarMarmita(nome: String, descricao: String, preco: Float, tipo: String, ingredientes: String, avaliacaoMedia: Float): String {
        if (nome.isBlank() || descricao.isBlank() || tipo.isBlank()) {
            return "Preencha todos os campos!"
        }

        val marmita = Marmita(
            nome = nome,
            descricao = descricao,
            preco = preco,
            tipo = tipo,
            ingredientes = ingredientes,

        )

        viewModelScope.launch {
            marmitaDao.inserir(marmita)
            carregarMarmitas()
            resultadoMensagem.value = "Marmita salva com sucesso!"
        }

        return "Marmita salva com sucesso!"
    }

    fun excluirMarmita(marmita: Marmita) {
        viewModelScope.launch {
            marmitaDao.deletar(marmita)
            carregarMarmitas()
            resultadoMensagem.value = "Marmita excluída com sucesso!"
        }
    }

    fun atualizarMarmita(id: Int, nome: String, descricao: String, preco: Float, tipo: String, ingredientes: String, avaliacaoMedia: Float): String {
        if (nome.isBlank() || descricao.isBlank() || tipo.isBlank()) {
            resultadoMensagem.value = "Preencha todos os campos!"
            return ""
        }

        viewModelScope.launch {
            val marmitaExistente = marmitaDao.buscarPorId(id)
            if (marmitaExistente != null) {
                val marmitaAtualizada = marmitaExistente.copy(
                    nome = nome,
                    descricao = descricao,
                    preco = preco,
                    tipo = tipo,
                    ingredientes = ingredientes,
                )
                marmitaDao.atualizar(marmitaAtualizada)
                carregarMarmitas()
                resultadoMensagem.value = "Marmita atualizada com sucesso!"
            } else {
                resultadoMensagem.value = "Erro ao atualizar marmita: Marmita não encontrada!"
            }
        }

        return "Marmita atualizada com sucesso!"
    }

    fun inserirMarmita(nome: Any, descricao: Any, preco: Any) {

    }
}
