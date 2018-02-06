package cashtracker.alibard.tm.com.test

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cashtracker.alibard.tm.com.base.BaseBindFragment
import cashtracker.alibard.tm.com.cashtracker.BR
import cashtracker.alibard.tm.com.cashtracker.R
import cashtracker.alibard.tm.com.cashtracker.databinding.TestFragmentBinding
import cashtracker.alibard.tm.com.ui.add_spending.SpendingVIewModel
import javax.inject.Inject


class TestFragment: BaseBindFragment<TestFragmentBinding,TestViewModel>(){
//    @Inject
//    lateinit var viewModelFactory: ViewModelProvider.Factory
//    @Inject lateinit var spendingModel: TestViewModel
    companion object {
        fun newInstance(): TestFragment = TestFragment()
    }
    override fun getBindingVariable(): Int = BR.viewModel

    override val layoutId: Int
        get() = R.layout.test_fragment

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       val  mViewDataBinding = DataBindingUtil.inflate<TestFragmentBinding>(inflater, R.layout.test_fragment, container, false)
//        mRootView = mViewDataBinding?.root
        return  mViewDataBinding?.root
    }
    override fun onCreteViewModel(): TestViewModel {
//        spendingModel = ViewModelProviders.of(this,viewModelFactory).get(TestViewModel::class.java)
//        return spendingModel
        return TestViewModel()
    }

}