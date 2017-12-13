package cashtracker.alibard.tm.com.activity.main


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import cashtracker.alibard.tm.com.cashtracker.R
import cashtracker.alibard.tm.com.cashtracker.databinding.MainActivityBinding
import cashtracker.alibard.tm.com.cashtracker.databinding.NavHeaderMain2Binding
import cashtracker.alibard.tm.com.model.User
import cashtracker.alibard.tm.com.ui.home.MainFragment
import cashtracker.alibard.tm.com.ui.settings.SettingsFragment
import cashtracker.alibard.tm.com.utils.extention.replaceFragmentInActivity
import cashtracker.alibard.tm.com.utils.extention.setupActionBar
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.main_activity.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val mainActivityModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }
    lateinit var navView: NavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainBinding = DataBindingUtil.setContentView<MainActivityBinding>(this, R.layout.main_activity)
        val binding = DataBindingUtil.inflate<NavHeaderMain2Binding>(layoutInflater, R.layout.nav_header_main2, mainBinding.navView, false)
        mainBinding.navView.addHeaderView(binding.root)
        navView = mainBinding.navView
        mainActivityModel.fillUser()
        subscribe(binding)
        binding.executePendingBindings()
        setupActionBar(R.id.toolbar) {
            setDisplayHomeAsUpEnabled(true)
        }

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        setUpDriverLL()
        fillFragment()

    }

    private fun fillFragment() {
//        navView.menu.getItem(0).isChecked = true
        supportFragmentManager.beginTransaction()
                .replace(R.id.mainContainer, MainFragment.newInstance())
                .commit()
    }

    private fun setUpDriverLL() {
        val toggle = ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
    }

    private fun subscribe(binding: NavHeaderMain2Binding) {
        val userObserver = Observer<User> { user ->
            binding.user = user
        }
        mainActivityModel.getUser().observe(this, userObserver)
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
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
