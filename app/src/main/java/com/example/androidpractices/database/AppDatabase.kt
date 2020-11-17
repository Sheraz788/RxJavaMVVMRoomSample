package com.example.androidpractices.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(PhotosEntity::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun photosDao() : PhotosDao

    //creating singleton of database to prevent multiple room database instances
    companion object{


        @Volatile
        private var appDatabase : AppDatabase? = null


        fun getAppDatabase(context: Context) : AppDatabase {

            return appDatabase
                ?: synchronized(this){
                val databaseInstance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "photos_database"
                ).build()
                appDatabase = databaseInstance
                databaseInstance
            }
        }



    }

}