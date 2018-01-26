package cashtracker.alibard.tm.com.repository.local

import cashtracker.alibard.tm.com.db.entities.Spending
import io.reactivex.Single


interface LocalRepository {

    fun getAllSpend(): Single<List<Spending>>
    fun insertSpend(spending: Spending):Single<Unit>
    fun updateSpend(spending: Spending):Single<Unit>

}