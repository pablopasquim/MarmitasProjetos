package com.example.marmitasapp.Model.Data.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.marmitasapp.Model.Entity.ItemPedido

@Dao
interface ItemPedidoDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserir(itemPedido: ItemPedido)

    @Delete
    suspend fun deletar(itemPedido: ItemPedido)

    @Query("SELECT * FROM itens_pedido WHERE pedidoId = :pedidoId")
    suspend fun buscarItensPorPedido(pedidoId: Int): List<ItemPedido>

    @Query("SELECT marmitas.nome, itens_pedido.quantidade, itens_pedido.precoUnitario FROM itens_pedido INNER JOIN marmitas ON itens_pedido.marmitaId = marmitas.id WHERE itens_pedido.pedidoId = :pedidoId")
    suspend fun buscarDetalhesItensPedido(pedidoId: Int): List<DetalhesItem>
}