package cashtracker.alibard.tm.com.repository.local

import cashtracker.alibard.tm.com.database.SpendingTable
import cashtracker.alibard.tm.com.pojo.Spending


interface LocalRepository {

    fun toDo()
    fun saveSpendint(spen : SpendingTable)
}