package com.example.marmitasapp.Model.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.marmitasapp.Model.Data.DAO.ClienteDAO
import com.example.marmitasapp.Model.Data.DAO.MarmitaDAO
import com.example.marmitasapp.Model.Data.DAO.PedidoDAO
import com.example.marmitasapp.Model.Entity.Cliente
import com.example.marmitasapp.Model.Entity.Marmita
import com.example.marmitasapp.Model.Entity.Pedido


@Database(entities = [Cliente::class, Marmita::class, Pedido::class],
    version = 2,
    exportSchema = true)
abstract class AppDataBase : RoomDatabase() {

    // Declarando os DAOs
    abstract fun clienteDao(): ClienteDAO
    abstract fun marmitaDao(): MarmitaDAO
    abstract fun pedidoDao(): PedidoDAO

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        // Definindo a migração entre versões
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {

                database.execSQL("ALTER TABLE Marmita ADD COLUMN novoCampo TEXT")
            }
        }


        fun getDatabase(context: Context): AppDataBase {
            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "app_database"
                )
                    .addMigrations(MIGRATION_1_2)
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
