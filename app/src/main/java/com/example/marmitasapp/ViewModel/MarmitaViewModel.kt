package com.example.marmitasapp.ViewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marmitasapp.Model.Data.DAO.MarmitaDAO
import com.example.marmitasapp.Model.Entity.Marmita
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MarmitaViewModel(private val marmitaDao: MarmitaDAO) : ViewModel() {

    private val _listaMarmitas = MutableStateFlow<List<Marmita>>(emptyList())
    val listaMarmitas = _listaMarmitas.asStateFlow()

    private val _resultadoMensagem = MutableStateFlow<String?>(null)
    val resultadoMensagem = _resultadoMensagem.asStateFlow()

    init {
        carregarMarmitas()
    }

    private fun carregarMarmitas() {
        viewModelScope.launch {
<<<<<<< HEAD
            try {
                _listaMarmitas.value = marmitaDao.listarTodas()
            } catch (e: Exception) {
                _resultadoMensagem.value = "Erro ao carregar marmitas: ${e.message}"
            }
        }
    }

    fun salvarMarmita(
        nome: String,
        descricao: String,
        preco: Float,
        tipo: String,
        ingredientes: String,
        avaliacaoMedia: Float
    ) {
=======
            listaMarmitas.value = marmitaDao.listarTodas()
        }
    }


    fun salvarMarmita(nome: String, descricao: String, preco: Float, tipo: String, ingredientes: String, avaliacaoMedia: Float): String {
>>>>>>> b46eea21ae1edb720710d9baa7db2575be778f75
        if (nome.isBlank() || descricao.isBlank() || tipo.isBlank()) {
            _resultadoMensagem.value = "Preencha todos os campos obrigatórios!"
            return
        }

        val marmita = Marmita(
            nome = nome,
            descricao = descricao,
            preco = preco,
            tipo = tipo,
            ingredientes = ingredientes,

        )

        viewModelScope.launch {
<<<<<<< HEAD
            try {
                marmitaDao.inserir(marmita)
                carregarMarmitas()
                _resultadoMensagem.value = "Marmita salva com sucesso!"
            } catch (e: Exception) {
                _resultadoMensagem.value = "Erro ao salvar marmita: ${e.message}"
=======
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
>>>>>>> b46eea21ae1edb720710d9baa7db2575be778f75
            }
        }
    }

    fun excluirMarmita(marmita: Marmita) {
        viewModelScope.launch {
            try {
                marmitaDao.deletar(marmita)
                carregarMarmitas()
                _resultadoMensagem.value = "Marmita excluída com sucesso!"
            } catch (e: Exception) {
                _resultadoMensagem.value = "Erro ao excluir marmita: ${e.message}"
            }
        }
    }

    fun atualizarMarmita(
        id: Int,
        nome: String,
        descricao: String,
        preco: Float,
        tipo: String,
        ingredientes: String,
        avaliacaoMedia: Float
    ) {
        if (nome.isBlank() || descricao.isBlank() || tipo.isBlank()) {
            _resultadoMensagem.value = "Preencha todos os campos obrigatórios!"
            return
        }

        viewModelScope.launch {
            try {
                val marmitaExistente = marmitaDao.buscarPorId(id)
                if (marmitaExistente != null) {
                    val marmitaAtualizada = marmitaExistente.copy(
                        nome = nome,
                        descricao = descricao,
                        preco = preco,
                        tipo = tipo,
                        ingredientes = ingredientes,
                        avaliacaoMedia = avaliacaoMedia
                    )
                    marmitaDao.atualizar(marmitaAtualizada)
                    carregarMarmitas()
                    _resultadoMensagem.value = "Marmita atualizada com sucesso!"
                } else {
                    _resultadoMensagem.value = "Erro: Marmita não encontrada!"
                }
            } catch (e: Exception) {
                _resultadoMensagem.value = "Erro ao atualizar marmita: ${e.message}"
            }
        }
    }

    fun inserirMarmita(nome: Any, descricao: Any, preco: Any) {

    }
}
