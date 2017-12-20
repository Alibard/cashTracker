package cashtracker.alibard.tm.com.repository.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = arrayOf(SpendingTable::class), version = 1)
public abstract class CashTrackarBase : RoomDatabase() {

    abstract fun spendingDao(): SpendingDao
}