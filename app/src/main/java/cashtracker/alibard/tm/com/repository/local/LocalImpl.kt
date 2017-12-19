package cashtracker.alibard.tm.com.repository.local

import cashtracker.alibard.tm.com.database.DataBase
import cashtracker.alibard.tm.com.database.SpendingTable
import cashtracker.alibard.tm.com.pojo.Spending
import javax.inject.Inject


class LocalImpl @Inject constructor(val dataBase: DataBase) : LocalRepository {
    override fun toDo() {
        dataBase.spendigDao().getTasks()
    }

    override fun saveSpendint(spen: SpendingTable) {
        dataBase.spendigDao().insertUser(spen)
    }

}