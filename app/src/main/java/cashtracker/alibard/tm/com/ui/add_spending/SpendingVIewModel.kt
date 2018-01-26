package cashtracker.alibard.tm.com.ui.add_spending

import android.arch.lifecycle.ViewModel
import android.util.Log
import cashtracker.alibard.tm.com.db.entities.Spending
import cashtracker.alibard.tm.com.repository.local.LocalRepository
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class SpendingVIewModel @Inject constructor() : ViewModel() {
    @Inject lateinit var repo: LocalRepository
    fun saveSpending(price: String, currency: String, type: String, notis: String) {
        repo.getAllSpend()
        Log.d("aa", "bb")
        if (isValid(price, currency, type, notis)) {
            repo.insertSpend(Spending(price = price.toInt(),currency =currency,description = notis))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        Log.d("aa", "")
                        getSize()
                    },{

                    })
        }

//        if (isValid(price, currency, type, notis)) {
//
//        }

    }

    private fun getSize() {
        repo.getAllSpend()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("aa", "" + it.size)
                }, { Log.d("aa", "" + it.message) })
//w
    }

    private fun isValid(price: String, currency: String, type: String, notis: String): Boolean {
        return true
    }
}