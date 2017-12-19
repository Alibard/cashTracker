package cashtracker.alibard.tm.com.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.IGNORE
import android.arch.persistence.room.Query


@Dao
interface SpendingDao{
    /**
     * Select all tasks from the tasks table.
     *
     * @return all tasks.
     */
    @Query("SELECT * FROM SpendingTable") fun getTasks(): List<SpendingTable>

    @Insert(onConflict = IGNORE)
    fun insertUser(spendingTable: SpendingTable)
}