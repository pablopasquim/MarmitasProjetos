package com.example.marmitasapp.Model.Entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "pedidos",
    foreignKeys = [
        ForeignKey(entity = Cliente::class, parentColumns = ["id"], childColumns = ["clienteId"]),
        ForeignKey(entity = Marmita::class, parentColumns = ["id"], childColumns = ["marmitaID"])  // Aqui a correção
    ]
)
data class Pedido(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val clienteId: Int,
    val marmitaID: Int,  // Corrigido aqui também
    val dataPedido: String,
    val status: String,
    val valorTotal: Float
)

