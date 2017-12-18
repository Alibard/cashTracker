package cashtracker.alibard.tm.com.activity.main


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import cashtracker.alibard.tm.com.cashtracker.R
import cashtracker.alibard.tm.com.model.User
import cashtracker.alibard.tm.com.ui.home.MainFragment
import cashtracker.alibard.tm.com.ui.settings.SettingsFragment
import cashtracker.alibard.tm.com.utils.extention.replaceFragmentInActivity
import cashtracker.alibard.tm.com.utils.extention.setupActionBar


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val mainActivityModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }
    private lateinit var drawerLayout: DrawerLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        mainActivityModel.fillUser()
        setupActionBar(R.id.toolbar) {
            setTitle(R.string.app_name)
            setHomeAsUpIndicator(R.drawable.ic_menu)
            setDisplayHomeAsUpEnabled(true)
        }
        mainActivityModel.fillUser()
        setupNavigationDrawer()
        findOrCreateViewFragment()
        subscribe()
    }

    private  fun  subscribe(){
//        final Observer<Long> elapsedTimeObserver = new Observer<Long>() {
//            @Override
//            public void onChanged(@Nullable final Long aLong) {
//                String newText = ChronoActivity3.this.getResources().getString(
//                    R.string.seconds, aLong);
//                ((TextView) findViewById(R.id.timer_textview)).setText(newText);
//                Log.d("ChronoActivity3", "Updating timer");
//            }
//        };

        val userObserver = Observer<User> {
          Log.d("taggss","1231412341234")
        }
        mainActivityModel.data.observe(this,userObserver)
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

        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

}
