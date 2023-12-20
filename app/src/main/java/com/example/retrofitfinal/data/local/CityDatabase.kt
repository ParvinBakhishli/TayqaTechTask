package com.example.retrofitfinal.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.retrofitfinal.model.local.CityEntity


@Database(entities = [CityEntity::class], version = 1)
abstract class CityDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao

    companion object{
        @Volatile
        private var instance: CityDatabase ?= null

        fun getDatabase(context: Context) = instance ?: synchronized(Unit){
            instance ?: Room.databaseBuilder(
                context.applicationContext,
                CityDatabase::class.java,
                "city_db"
            ).fallbackToDestructiveMigration().build().also {
                instance = it
            }
        }

    }

}