package cashtracker.alibard.tm.com.ui.add_spending

import android.arch.lifecycle.ViewModel
import android.util.Log
import cashtracker.alibard.tm.com.repository.local.LocalRepository
import javax.inject.Inject


class SpendingVIewModel @Inject constructor(val rep: LocalRepository) : ViewModel() {
    fun saveSpending() {
        Log.d("TAGGGGG",": "+rep.getAllSpend().size)
    }
}