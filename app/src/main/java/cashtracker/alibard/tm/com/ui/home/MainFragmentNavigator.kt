package cashtracker.alibard.tm.com.ui.home

import android.view.View
import cashtracker.alibard.tm.com.base.BaseNavigator
import cashtracker.alibard.tm.com.pojo.Spending


interface MainFragmentNavigator : BaseNavigator {
    fun fillData(it: List<Spending>)
    fun onListItemClick(view: View)
}