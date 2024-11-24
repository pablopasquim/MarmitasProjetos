import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marmitasapp.Model.Data.DAO.PedidoDAO
import com.example.marmitasapp.Model.Entity.Pedido
import kotlinx.coroutines.launch

class PedidoViewModel(private val pedidoDao: PedidoDAO) : ViewModel() {

    var resultadoMensagem = mutableStateOf("")


    fun realizarPedido(clienteId: Int, marmitaId: Int, valorTotal: Float, status: String) {
        viewModelScope.launch {
            val pedido = Pedido(
                clienteId = clienteId,
                marmitaID = marmitaId,
                valorTotal = valorTotal,
                status = status,
                dataPedido = "2024-11-23"
            )
            pedidoDao.inserir(pedido)
            resultadoMensagem.value = "Pedido realizado com sucesso!"
        }
    }


    fun listarPedidos() {
        viewModelScope.launch {

            val pedidos = pedidoDao.listarTodos()

        }
    }

    // Função para excluir um pedido
    fun excluirPedido(pedido: Pedido) {
        viewModelScope.launch {
            pedidoDao.deletar(pedido)
            resultadoMensagem.value = "Pedido excluído com sucesso!"
        }
    }


    fun atualizarPedido(id: Int, status: String) {
        viewModelScope.launch {
            val pedidoExistente = pedidoDao.buscarPorId(id)
            if (pedidoExistente != null) {
                val pedidoAtualizado = pedidoExistente.copy(status = status)
                pedidoDao.atualizar(pedidoAtualizado)
                resultadoMensagem.value = "Pedido atualizado com sucesso!"
            } else {
                resultadoMensagem.value = "Pedido não encontrado!"
            }
        }
    }
}
