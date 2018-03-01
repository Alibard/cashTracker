package cashtracker.alibard.tm.com.ui.add_spending

import cashtracker.alibard.tm.com.base.BaseViewModel
import cashtracker.alibard.tm.com.db.entities.Spending
import cashtracker.alibard.tm.com.repository.local.LocalRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class SpendingVIewModel @Inject constructor() : BaseViewModel<SpendingNavigator>() {
    @Inject
    lateinit var repo: LocalRepository
    val text = "1"
    fun saveSpending(price: String, currency: String, type: String, notice: String) {
        if (isValid(price, currency, type, notice)) {
            navigator?.showLoad()
            repo.insertSpend(Spending(price = price.toInt(), currency = currency, description = notice, type = type))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        navigator?.hideLoad()
                        navigator?.onSuccess()
                    }, {
                        navigator?.hideLoad()
                        navigator?.onError(it)
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
        return price.isNotEmpty() && currency.isNotEmpty() && type.isNotEmpty() && notis.isNotEmpty()
    }

    fun fillData() {
        repo.getTypesSettings()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    navigator?.fillData(it)
                }
                        ,{})

    }
}