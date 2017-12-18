package cashtracker.alibard.tm.com.activity.additional_activity

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import cashtracker.alibard.tm.com.cashtracker.R
import cashtracker.alibard.tm.com.ui.add_spending.AddSpendingFragment
import cashtracker.alibard.tm.com.utils.extention.replaceFragmentInActivity


class AdditionalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.additional_activity)
        when(intent.getStringExtra("fragment")){
            AddSpendingFragment.TAG ->{
                this.replaceFragmentInActivity(AddSpendingFragment.newInstance(), R.id.additionalContainer)
            }
        }

    }

}