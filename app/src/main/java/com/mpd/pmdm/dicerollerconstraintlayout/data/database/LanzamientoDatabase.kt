package com.mpd.pmdm.dicerollerconstraintlayout.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Lanzamiento::class], version = 1)
abstract class LanzamientoDatabase: RoomDatabase() {

    abstract fun lanzamientoDao(): LanzamientoDao

    companion object{
        //Marcamos como volatile para que cualquier hilo vea el valor actual del dato
        @Volatile
        private var INSTANCE: LanzamientoDatabase? = null

        //Nótese el uso del operador Elvis
        fun getDataBase(context: Context): LanzamientoDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context,
                    LanzamientoDatabase::class.java,
                    "app_database"
                )
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }

}