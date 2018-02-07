package cashtracker.alibard.tm.com.activity.test

import android.app.Activity
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import cashtracker.alibard.tm.com.cashtracker.R
import cashtracker.alibard.tm.com.cashtracker.databinding.TestActivityBinding


class TestActivity :AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val test : TestActivityBinding = DataBindingUtil.setContentView(this, R.layout.test_activity)
        val user = User("ololo1","ololo2")
        test.model = user
//        val ololo: TestActivityBinding = DataBindingUtil.setContentView(this, R.layout.test_activity)!!
//        val user = User("ololo1","ololo2")
//        ololo.viewModel
//        val mainBinding: TestActivityBinding =  DataBindingUtil.setContentView(this, R.layout.test_activity)!!
//        val mainBinding: TestActivityBinding =  DataBindingUtil.setContentView(this, R.layout.test_activity)!!
//        mainBinding
    }
}