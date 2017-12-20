package cashtracker.alibard.tm.com.repository.local

import cashtracker.alibard.tm.com.pojo.Spending
import cashtracker.alibard.tm.com.repository.database.SpendingTable


interface LocalRepository {
    fun getAllSpend(): List<SpendingTable>

}