package com.example.marmitasapp.Model.Data.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.marmitasapp.Model.Entity.Marmita

@Dao
interface MarmitaDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserir(marmita: Marmita)

    @Update
    suspend fun atualizar(marmita: Marmita)

    @Delete
    suspend fun deletar(marmita: Marmita)

    @Query("SELECT * FROM marmitas WHERE id = :id")
    suspend fun buscarPorId(id: Int): Marmita?

    @Query("SELECT * FROM marmitas WHERE tipo LIKE :tipo ORDER BY preco ASC")
    suspend fun buscarPorTipo(tipo: String): List<Marmita>

    @Query("SELECT * FROM marmitas WHERE nome LIKE :nome")
    suspend fun buscarPorNome(nome: String): List<Marmita>

    @Query("SELECT * FROM marmitas")
    suspend fun listarTodas(): List<Marmita>
}


