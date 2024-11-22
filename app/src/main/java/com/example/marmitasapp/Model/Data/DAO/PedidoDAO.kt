package com.example.marmitasapp.Model.Data.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.marmitasapp.Model.Entity.Pedido

@Dao
interface PedidoDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserir(pedido: Pedido)

    @Update
    suspend fun atualizar(pedido: Pedido)

    @Delete
    suspend fun deletar(pedido: Pedido)

    @Query("SELECT * FROM pedidos WHERE clienteId = :clienteId ORDER BY dataPedido DESC")
    suspend fun buscarPorCliente(clienteId: Int): List<Pedido>

    @Query("SELECT * FROM pedidos WHERE id = :id")
    suspend fun buscarPorId(id: Int): Pedido?

    @Query("SELECT * FROM pedidos")
    suspend fun listarTodos(): List<Pedido>
}


