package cashtracker.alibard.tm.com.repository.local

import cashtracker.alibard.tm.com.db.entities.Spending
import io.reactivex.Completable
import io.reactivex.Single


interface LocalRepository {

    fun getAllSpend(): Single<List<Spending>>
    fun insertSpend(spending: Spending):Completable
    fun updateSpend(spending: Spending):Completable

}