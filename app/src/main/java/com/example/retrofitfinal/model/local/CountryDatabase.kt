package com.example.retrofitfinal.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CountryEntity::class], version = 1)
abstract class CountryDatabase : RoomDatabase() {

    abstract fun countryDao(): CountryDao

    companion object {
        @Volatile
        private var instance: CountryDatabase? = null

        fun getDatabase(context: Context) = instance ?: synchronized(Unit) {
            instance ?: Room.databaseBuilder(
                context.applicationContext,
                CountryDatabase::class.java,
                "country_db"
            ).fallbackToDestructiveMigration().build().also {
                instance = it
            }
        }
    }

}