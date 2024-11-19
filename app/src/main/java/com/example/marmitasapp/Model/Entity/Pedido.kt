package com.example.marmitasapp.Model.Entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "pedidos",
    foreignKeys = [ForeignKey(entity = Cliente::class, parentColumns = ["id"], childColumns = ["clientesId"])]
)
data class Pedido(
    @PrimaryKey(autoGenerate = true) val Id: Int = 0,
    val clientId: Int,
    val dataPedidos: String,
    val status: String,
    val valorTotal: Float
)
