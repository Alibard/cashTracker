package cashtracker.alibard.tm.com.ui.home

import cashtracker.alibard.tm.com.base.BaseViewModel
import cashtracker.alibard.tm.com.pojo.Spending
import cashtracker.alibard.tm.com.repository.local.LocalRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class MainFragmentModel @Inject constructor() : BaseViewModel<MainFragmentNavigator>() {
    @Inject
    lateinit var repo: LocalRepository
    var isEmpty : Boolean = true
    fun getAllSpending() {
        repo.getAllSpend()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { list ->
                    list.map {
                        Spending.fromDao(it)
                    }
                }
                .subscribe({
                    navigator?.fillData(it)
                }, {
                    navigator?.onError(it)
                })
    }

}