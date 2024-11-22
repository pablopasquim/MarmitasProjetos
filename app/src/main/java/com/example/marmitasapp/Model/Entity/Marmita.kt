package com.example.marmitasapp.Model.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "marmitas")
data class Marmita(

    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nome: String,
    val descricao: String,
    val preco: Float,
    val tipo: String,
    val ingredientes: String,
    val avaliacaoMedia: Float,

)
