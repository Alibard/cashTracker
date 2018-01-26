package cashtracker.alibard.tm.com.db.dao

import android.arch.persistence.room.*
import cashtracker.alibard.tm.com.db.entities.Spending


@Dao
interface SpendingDao {
    @Query("SELECT * FROM spending")
    fun getAllSpending(): List<Spending>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insetSpending(spend: Spending)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun  updageSpending(spend: Spending)
}