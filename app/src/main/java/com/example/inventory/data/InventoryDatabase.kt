package com.example.inventory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//adalah kelas yang digunakan untuk mengakses database dengan menggunakan Room
@Database(entities = [Item::class], version = 1, exportSchema = false) //digunakan untuk menandai sebuah kelas sebagai entitas, yang dimana merepresentasikan sebuah tabel dalam database
abstract class InventoryDatabase : RoomDatabase() { //digunakan untuk menandai sebuah kelas sebagai database Room

    abstract fun itemDao(): ItemDao

    companion object { //digunakan untuk menandai sebuah kelas sebagai kelas statis yang berisi metode dan properti statis yang terkait dengan kelas tersebut
        @Volatile //digunakan untuk memastikan bahwa nilai variabel hanya diakses atau diubah oleh satu thread saja
        private var Instance: InventoryDatabase? = null

        fun getDatabase(context: Context): InventoryDatabase { //digunakan untuk mengambil instance dari database
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, InventoryDatabase::class.java, "item_database") //digunakan untuk membangun sebuah database Room baru
                    .build()
                    .also { Instance = it }
            }
        }
    }
}