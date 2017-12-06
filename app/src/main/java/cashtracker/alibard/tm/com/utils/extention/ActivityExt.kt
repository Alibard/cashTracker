package cashtracker.alibard.tm.com.utils.extention

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity


fun AppCompatActivity.setupActionBar(@IdRes toolbarId: Int, action: ActionBar.() -> Unit) {
    setSupportActionBar(findViewById(toolbarId))
    supportActionBar?.run {
        action()
    }
}

fun AppCompatActivity.replaceFragmentInActivity(fragment: Fragment, frameId: Int) {
    supportFragmentManager.transact {
        replace(frameId, fragment)
    }
}


private inline fun FragmentManager.transact(action: FragmentTransaction.() -> Unit) {
    beginTransaction().apply {
        action()
    }.commit()
}


fun AppCompatActivity.replaceFragmentInActivityWithBack(fragment: Fragment, frameId: Int, tag: String) {
    supportFragmentManager.transactWithBack(tag) {
        replace(frameId, fragment)
    }
}

private inline fun FragmentManager.transactWithBack(tag: String, action: FragmentTransaction.() -> Unit) {
    beginTransaction().addToBackStack(tag).apply {
        action()
    }.commit()
}