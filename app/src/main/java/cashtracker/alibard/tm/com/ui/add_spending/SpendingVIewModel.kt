package cashtracker.alibard.tm.com.ui.add_spending

import android.arch.lifecycle.ViewModel
import android.view.ViewManager
import cashtracker.alibard.tm.com.database.SpendingTable
import cashtracker.alibard.tm.com.repository.local.LocalRepository
import javax.inject.Inject


class SpendingVIewModel(val rep: LocalRepository) : ViewModel() {

    fun saveSpending() {
        rep.saveSpendint(SpendingTable())
    }
}