package com.example.retrofitfinal.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.retrofitfinal.model.local.PeopleEntity


@Database(entities = [PeopleEntity::class], version = 1)
abstract class PeopleDatabase : RoomDatabase() {
    abstract fun peopleDao(): PeopleDao

    companion object{
        @Volatile
        private var instance: PeopleDatabase ?= null

        fun getDatabase(context: Context) = instance ?: synchronized(Unit){
            instance ?: Room.databaseBuilder(
                context.applicationContext,
                PeopleDatabase::class.java,
                "people_db"
            ).fallbackToDestructiveMigration().build().also {
                instance = it
            }
        }
    }
}