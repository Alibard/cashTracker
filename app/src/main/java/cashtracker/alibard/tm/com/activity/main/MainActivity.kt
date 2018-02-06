package cashtracker.alibard.tm.com.activity.main


import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.res.Configuration
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import cashtracker.alibard.tm.com.cashtracker.R
import cashtracker.alibard.tm.com.pojo.User
import cashtracker.alibard.tm.com.test.TestFragment
import cashtracker.alibard.tm.com.ui.home.MainFragment
import cashtracker.alibard.tm.com.ui.settings.SettingsFragment
import cashtracker.alibard.tm.com.utils.extention.loadBigImage
import cashtracker.alibard.tm.com.utils.extention.replaceFragmentInActivity
import cashtracker.alibard.tm.com.utils.extention.setupActionBar
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.nav_header_main2.view.*
import javax.inject.Inject


class MainActivity : AppCompatActivity(),
        NavigationView.OnNavigationItemSelectedListener,
        MainActivityNavigator {
    private lateinit var drawerLayout: DrawerLayout


    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject lateinit var androidInjector: DispatchingAndroidInjector<Fragment>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setupActionBar(R.id.toolbar) {
            setTitle(R.string.app_name)
            setHomeAsUpIndicator(R.drawable.ic_menu)
            setDisplayHomeAsUpEnabled(true)
        }
        setupNavigationDrawer()
        findOrCreateViewFragment()
        subscribe()


    }

    private fun subscribe() {

    }

    private fun findOrCreateViewFragment() =
            supportFragmentManager.findFragmentById(R.id.mainContainer) ?:
                    MainFragment.newInstance().also {
                        replaceFragmentInActivity(it, R.id.mainContainer)
                    }

    private fun setupNavigationDrawer() {
        drawerLayout = (findViewById<DrawerLayout>(R.id.drawerLayout)).apply {
            setStatusBarBackground(R.color.colorPrimaryDark)
        }
        setupDrawerContent(findViewById(R.id.nav_view))
    }

    private fun setupDrawerContent(navigationView: NavigationView) {
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main2, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) =
            when (item.itemId) {
                android.R.id.home -> {
                    drawerLayout.openDrawer(GravityCompat.START)
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_camera -> {
                if (!item.isChecked)
                    replaceFragmentInActivity(MainFragment.newInstance(), R.id.mainContainer)
            }

            R.id.nav_settings -> {
                if (!item.isChecked)
                    replaceFragmentInActivity(SettingsFragment.newInstance(), R.id.mainContainer)

            }
            R.id.nav_test -> {
                if (!item.isChecked)
                    replaceFragmentInActivity(TestFragment.newInstance(), R.id.mainContainer)

            }

        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

}
