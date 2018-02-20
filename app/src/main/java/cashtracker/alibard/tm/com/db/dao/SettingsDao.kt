package cashtracker.alibard.tm.com.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import cashtracker.alibard.tm.com.db.entities.FullSettings
import cashtracker.alibard.tm.com.db.entities.SpendingType
import cashtracker.alibard.tm.com.db.entities.StructureSettings


@Dao
interface SettingsDao {
    @Query("SELECT * from settings")
    fun getUsersWithRepos(): List<FullSettings>

    @Query("SELECT * from spending_type")
    fun getAllTypes(): List<SpendingType>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStructureSettings(structureSettings: StructureSettings)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSpendingType(spendingType: List<SpendingType>)
}