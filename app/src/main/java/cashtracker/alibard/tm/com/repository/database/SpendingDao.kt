package cashtracker.alibard.tm.com.repository.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import cashtracker.alibard.tm.com.pojo.Spending


@Dao interface SpendingDao{

    /**
     * Add new Spend to database
     */
    @Insert (onConflict = OnConflictStrategy.IGNORE)fun addNewSpend(spend : SpendingTable)


    @Query ("SELECT * FROM SpendingTable")fun getAllSpends():List<SpendingTable>
}