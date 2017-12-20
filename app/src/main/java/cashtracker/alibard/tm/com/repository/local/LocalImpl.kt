package cashtracker.alibard.tm.com.repository.local

import android.content.Context
import cashtracker.alibard.tm.com.pojo.Spending
import cashtracker.alibard.tm.com.repository.database.SpendingDao
import cashtracker.alibard.tm.com.repository.database.SpendingTable
import javax.inject.Inject


class LocalImpl @Inject constructor(val dao: SpendingDao) : LocalRepository {
    var spendingDao :SpendingDao = dao

    override fun getAllSpend(): List<SpendingTable> {
        return spendingDao.getAllSpends()
    }
}