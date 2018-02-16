package cashtracker.alibard.tm.com.ui.home.adapter

import android.databinding.ViewDataBinding

import cashtracker.alibard.tm.com.cashtracker.R
import cashtracker.alibard.tm.com.pojo.Spending
import cashtracker.alibard.tm.com.ui.home.MainFragmentNavigator
import com.airbnb.epoxy.DataBindingEpoxyModel
import com.airbnb.epoxy.EpoxyAdapter
import com.airbnb.epoxy.databinding.BR


class MainAdapter(listItems: List<Spending>, private var mainFragment: MainFragmentNavigator) : EpoxyAdapter() {
    init {
        listItems.forEach {
            addModel(TestModel(it,mainFragment))
        }

    }

    fun fillModels(listItems: List<Spending>) {
        listItems.forEach {
            addModel(TestModel(it, mainFragment))
        }
    }


    class TestModel(val item: Spending, private val mainFragment: MainFragmentNavigator) : DataBindingEpoxyModel() {
        override fun setDataBindingVariables(binding: ViewDataBinding) {
            binding.setVariable(BR.data, item)
            binding.setVariable(BR.clickHandler, mainFragment)
        }

        override fun getDefaultLayout(): Int = R.layout.view_holder_page_header
    }
}