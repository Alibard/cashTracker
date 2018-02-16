package cashtracker.alibard.tm.com.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import cashtracker.alibard.tm.com.db.dao.SettingsDao
import cashtracker.alibard.tm.com.db.dao.SpendingDao
import cashtracker.alibard.tm.com.db.entities.Spending
import cashtracker.alibard.tm.com.db.entities.StructureSettings

@Database (entities = arrayOf(Spending::class, StructureSettings::class),version = 1)
abstract class CashTrackerDataBase :RoomDatabase() {

    abstract fun spendingDao(): SpendingDao
    abstract fun settingsDao(): SettingsDao
}