package com.example.marmitasapp.Model.Data.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.marmitasapp.Model.Entity.Cliente

@Dao
interface ClienteDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserir(cliente: Cliente)

    @Update
    suspend fun atualizar(cliente: Cliente)

    @Delete
    suspend fun deletar(cliente: Cliente)

    @Query("SELECT * FROM clientes WHERE id = :id")
    suspend fun buscarPorId(id: Int): Cliente?

    @Query("SELECT * FROM clientes WHERE email LIKE :email")
    suspend fun buscarPorEmail(email: String): List<Cliente>
}