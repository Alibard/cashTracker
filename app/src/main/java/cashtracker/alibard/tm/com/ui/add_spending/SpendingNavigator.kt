package cashtracker.alibard.tm.com.ui.add_spending

import cashtracker.alibard.tm.com.base.BaseNavigator
import cashtracker.alibard.tm.com.db.entities.SpendingType


interface SpendingNavigator: BaseNavigator {
    fun onSuccess()
    fun fillData(it: List<SpendingType>)
}