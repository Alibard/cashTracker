package cashtracker.alibard.tm.com.ui.add_spending

import android.util.Log
import cashtracker.alibard.tm.com.base.BaseViewModel
import cashtracker.alibard.tm.com.db.entities.Spending
import cashtracker.alibard.tm.com.repository.local.LocalRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class SpendingVIewModel @Inject constructor() : BaseViewModel<SpendingNavigator>() {
    @Inject lateinit var repo: LocalRepository
    fun saveSpending(price: String, currency: String, type: String, notice: String) {
        if (isValid(price, currency, type, notice)) {
            repo.insertSpend(Spending(price = price.toInt(),currency =currency,description = notice))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        navigator?.onSuccess()
                    },{

                    })
        }
    }

    private fun getSize() {
        repo.getAllSpend()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                }, {
                })
    }

    private fun isValid(price: String, currency: String, type: String, notis: String): Boolean {
        return price.isNotEmpty()&&currency.isNotEmpty()&&type.isNotEmpty()&&notis.isNotEmpty()
    }
}