package cashtracker.alibard.tm.com.repository.local

import android.util.Log
import cashtracker.alibard.tm.com.db.CashTrackerDataBase
import cashtracker.alibard.tm.com.db.entities.Spending
import io.reactivex.Completable
import io.reactivex.Single

import javax.inject.Inject


class LocalImpl @Inject constructor(private val dataBase: CashTrackerDataBase) : LocalRepository {
    override fun updateSpend(spending: Spending): Completable {
        return Completable.fromAction { ( dataBase.spendingDao().updateSpending(spending))}//.fromCallable { dataBase.spendingDao().updateSpending(spending) }
    }

    override fun insertSpend(spending: Spending): Completable{
        return  Completable.fromAction { dataBase.spendingDao().insetSpending(spending) }
    }

    override fun getAllSpend(): Single<List<Spending>> {
        Log.d("op op", "op op")
        return Single.fromCallable { dataBase.spendingDao().getAllSpending() }
    }

}