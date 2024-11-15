package Nots.db

import Nots.NotsModel.NotModel
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NotsDao {

    // Tüm notları getirme
    @Query("SELECT * FROM NotModel")
    suspend fun getAllNotes(): List<NotModel>

    // id ile not getirme
    @Query("SELECT * FROM NotModel WHERE id = :id")
    suspend fun findById(id: Int): NotModel?

    // Not ekleme
    @Insert
    suspend fun insert(not: NotModel)

     @Delete
    suspend fun delete(not:NotModel)




}