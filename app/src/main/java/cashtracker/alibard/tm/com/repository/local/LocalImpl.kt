package cashtracker.alibard.tm.com.repository.local

import cashtracker.alibard.tm.com.db.CashTrackerDataBase
import cashtracker.alibard.tm.com.db.entities.FullSettings
import cashtracker.alibard.tm.com.db.entities.Spending
import cashtracker.alibard.tm.com.db.entities.SpendingType
import cashtracker.alibard.tm.com.db.entities.StructureSettings
import io.reactivex.Completable
import io.reactivex.Single

import javax.inject.Inject


class LocalImpl @Inject constructor(private val dataBase: CashTrackerDataBase) : LocalRepository {
    override fun updateSpend(spending: Spending): Completable {
        return Completable.fromAction { (dataBase.spendingDao().updateSpending(spending)) }//.fromCallable { dataBase.spendingDao().updateSpending(spending) }
    }

    override fun insertSpend(spending: Spending): Completable {
        return Completable.fromAction { dataBase.spendingDao().insetSpending(spending) }
    }

    override fun getAllSpend(): Single<List<Spending>> {
        return Single.fromCallable { dataBase.spendingDao().getAllSpending() }
    }

    override fun getInitialSettings(): Single<List<FullSettings>> {
        return Single.fromCallable { dataBase.settingsDao().getUsersWithRepos() }
    }

    override fun insertSettings(structureSettings: StructureSettings): Completable {
        return Completable.fromAction { dataBase.settingsDao().insertStructureSettings(structureSettings) }
    }

    override fun insertBaseType(spendingType: List<SpendingType>): Completable {
        return Completable.fromAction { dataBase.settingsDao().insertSpendingType(spendingType) }
    }

}
