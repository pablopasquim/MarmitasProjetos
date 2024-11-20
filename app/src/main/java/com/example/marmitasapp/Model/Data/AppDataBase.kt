package com.example.marmitasapp.Model.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.marmitasapp.Model.Data.DAO.ClienteDAO
import com.example.marmitasapp.Model.Data.DAO.ItemPedidoDAO
import com.example.marmitasapp.Model.Data.DAO.MarmitaDAO
import com.example.marmitasapp.Model.Data.DAO.PedidoDAO
import com.example.marmitasapp.Model.Entity.Cliente
import com.example.marmitasapp.Model.Entity.ItemPedido
import com.example.marmitasapp.Model.Entity.Marmita
import com.example.marmitasapp.Model.Entity.Pedido

@Database(entities = [Cliente::class, Marmita::class, Pedido::class, ItemPedido::class],
    version = 1,
    exportSchema = true)
abstract class AppDataBase : RoomDatabase() {

    abstract fun clienteDao(): ClienteDAO
    abstract fun marmitaDao(): MarmitaDAO
    abstract fun pedidoDao(): PedidoDAO
    abstract fun itemPedidoDao(): ItemPedidoDAO

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}