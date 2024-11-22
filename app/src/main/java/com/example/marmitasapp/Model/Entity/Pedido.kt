package com.example.marmitasapp.Model.Entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "pedidos",
    foreignKeys = [ForeignKey(entity = Cliente::class, parentColumns = ["id"], childColumns = ["clienteId"])]
)
data class Pedido(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val clienteId: Int,
    val dataPedido: String,
    val status: String,
    val valorTotal: Float
)
