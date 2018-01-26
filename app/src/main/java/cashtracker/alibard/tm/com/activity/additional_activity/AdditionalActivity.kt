package cashtracker.alibard.tm.com.activity.additional_activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import cashtracker.alibard.tm.com.cashtracker.R
import cashtracker.alibard.tm.com.ui.add_spending.AddSpendingFragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject


class AdditionalActivity : AppCompatActivity(),HasSupportFragmentInjector {
    @Inject lateinit var fragmentInjector :DispatchingAndroidInjector<Fragment>
    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.additional_activity)
        when(intent.getStringExtra("fragment")){
            AddSpendingFragment.TAG ->{
                supportFragmentManager.beginTransaction()
                        .replace(R.id.additionalContainer,AddSpendingFragment.newInstance())
                        .commit()
//                supportFragmentManager.findFragmentById(R.id.additionalContainer) ?:
//                        AddSpendingFragment.newInstance().also {
//                            replaceFragmentInActivity(it, R.id.additionalContainer)
//                        }
            }
        }

    }

}