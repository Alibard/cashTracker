package cashtracker.alibard.tm.com.base

import android.content.Context
import android.databinding.DataBindingComponent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.AndroidSupportInjection


abstract class BaseBindFragment<T : ViewDataBinding, V : BaseViewModel<*>> : Fragment() {

    abstract fun onCreteViewModel(): V

    var mViewDataBinding: T? = null
        private set

    var viewModel: V? = null
        private set

    private var mRootView: View? = null
    override fun onAttach(context: Context?) {
//        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = onCreteViewModel()

    }

//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        mViewDataBinding = DataBindingUtil.inflate<T>(inflater, layoutId, container, false)
//        mRootView = mViewDataBinding?.root
//        return mRootView
//    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        performDataBinding()
    }

    private fun performDataBinding() {
        mViewDataBinding?.setVariable(getBindingVariable(), viewModel)
        mViewDataBinding?.executePendingBindings()
    }

    abstract fun getBindingVariable(): Int
    /**
     * @return layout resource id
     */
    @get:LayoutRes
    abstract val layoutId: Int
}