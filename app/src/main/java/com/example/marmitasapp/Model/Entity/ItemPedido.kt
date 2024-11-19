package com.example.marmitasapp.Model.Entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "itens_pedido",
    foreignKeys = [
        ForeignKey(entity = Pedido::class, parentColumns = ["id"], childColumns = ["pedidoId"]),
        ForeignKey(entity = Marmita::class, parentColumns = ["id"], childColumns = ["marmitaId"])
    ]
)
data class ItemPedido(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val pedidoId: Int,
    val marmitaId: Int,
    val quantidade: Int,
    val precoUnitario: Float
)
