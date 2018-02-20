package cashtracker.alibard.tm.com.ui.home

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import cashtracker.alibard.tm.com.activity.additional_activity.AdditionalActivity
import cashtracker.alibard.tm.com.base.BaseBindFragment
import cashtracker.alibard.tm.com.cashtracker.BR
import cashtracker.alibard.tm.com.cashtracker.R
import cashtracker.alibard.tm.com.cashtracker.databinding.MainFragmentBinding
import cashtracker.alibard.tm.com.pojo.Spending
import cashtracker.alibard.tm.com.ui.add_spending.AddSpendingFragment
import cashtracker.alibard.tm.com.ui.home.adapter.MainAdapter
import com.airbnb.epoxy.EpoxyRecyclerView
import kotlinx.android.synthetic.main.main_fragment.*
import javax.inject.Inject


class MainFragment :
        BaseBindFragment<MainFragmentBinding, MainFragmentModel>(),
        MainFragmentNavigator {
    override fun onListItemClick(view: View) {
        Log.d("tag", "CLiCK")
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var mainModel: MainFragmentModel

    override fun onCreteViewModel(): MainFragmentModel {
        mainModel = ViewModelProviders.of(activity, viewModelFactory).get(MainFragmentModel::class.java)
        return mainModel
    }

    override val layoutId: Int
        get() = R.layout.main_fragment

    override fun getBindingVariable(): Int = BR.maidActivityModel

    companion object {

        fun newInstance(): MainFragment = MainFragment()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    private val adapter: MainAdapter by lazy {
        MainAdapter(ArrayList(), this)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainModel.navigator = this
        val recyclerView = view?.findViewById<EpoxyRecyclerView>(R.id.recycler_view)
        context
        val layoutManager = LinearLayoutManager(context)
        recyclerView?.layoutManager = layoutManager
        recyclerView?.adapter = adapter
        fab.setOnClickListener {
            val intent = Intent(activity, AdditionalActivity::class.java)
            intent.putExtra("fragment", AddSpendingFragment.TAG)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        mainModel.getAllSpending()
    }

    override fun fillData(it: List<Spending>) {
        if (it.isEmpty()) {
            showEmpty()
        } else {
            adapter.fillModels(it)
        }

    }

    private fun showEmpty() {

    }
}



