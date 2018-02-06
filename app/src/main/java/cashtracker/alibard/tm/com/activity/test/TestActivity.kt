package cashtracker.alibard.tm.com.activity.test

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import cashtracker.alibard.tm.com.cashtracker.R
import cashtracker.alibard.tm.com.cashtracker.databinding.TestActivityBinding


class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.test_activity)
        DataBindingUtil.setContentView<TestActivityBinding>(this, R.layout.test_activity)
    }
}