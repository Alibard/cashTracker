package cashtracker.alibard.tm.com.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = arrayOf(SpendingTable::class), version = 1)
abstract class DataBase : RoomDatabase(){
    abstract fun spendigDao(): SpendingDao

    companion object {

        private var INSTANCE: DataBase? = null

        private val lock = Any()

        fun getInstance(context: Context): DataBase {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            DataBase::class.java, "Spending.db")
                            .build()
                }
                return INSTANCE!!
            }
        }
    }
}