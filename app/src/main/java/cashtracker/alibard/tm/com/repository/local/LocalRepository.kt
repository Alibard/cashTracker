package cashtracker.alibard.tm.com.repository.local

import cashtracker.alibard.tm.com.db.entities.FullSettings
import cashtracker.alibard.tm.com.db.entities.Spending
import cashtracker.alibard.tm.com.db.entities.SpendingType
import cashtracker.alibard.tm.com.db.entities.StructureSettings
import io.reactivex.Completable
import io.reactivex.Single


interface LocalRepository {

    fun getAllSpend(): Single<List<Spending>>
    fun insertSpend(spending: Spending): Completable
    fun updateSpend(spending: Spending): Completable
    fun getInitialSettings(): Single<List<FullSettings>>
    fun insertSettings(structureSettings: StructureSettings): Completable
    fun insertBaseType(spendingType: List<SpendingType>): Completable

}