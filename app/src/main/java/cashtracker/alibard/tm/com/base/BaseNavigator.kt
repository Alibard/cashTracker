package cashtracker.alibard.tm.com.base

import android.app.AlertDialog


interface BaseNavigator {

    fun onError(it: Throwable)
    fun showLoad()
    fun hideLoad()
}