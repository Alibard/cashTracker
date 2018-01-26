package cashtracker.alibard.tm.com.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import cashtracker.alibard.tm.com.db.dao.SpendingDao
import cashtracker.alibard.tm.com.db.entities.Spending

@Database (entities = arrayOf(Spending::class),version = 1)
abstract class CashTrackerDataBase :RoomDatabase() {

    abstract fun spendingDao(): SpendingDao

}