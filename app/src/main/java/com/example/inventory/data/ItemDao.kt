package com.example.inventory.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

//Membuat interface untuk mengakses data dari database
@Dao //digunakan untuk menandai sebuah interface sebagai DAO (Data Access Object)
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE) //digunakan untuk menambahkan item ke dalam database
    suspend fun insert(item: Item)

    @Update //digunakan untuk memperbarui item yang ada di dalam database
    suspend fun update(item: Item)

    @Delete //digunakan untuk menghapus item dari dalam database
    suspend fun delete(item: Item)

    @Query("SELECT * from items WHERE id = :id") //digunakan untuk mengambil item berdasarkan id
    fun getItem(id: Int): Flow<Item>

    @Query("SELECT * from items ORDER BY name ASC") //digunakan untuk mengambil semua item dari database dan mengurutkannya berdasarkan nama
    fun getAllItems(): Flow<List<Item>>
}