package cashtracker.alibard.tm.com.ui.add_spending

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import cashtracker.alibard.tm.com.base.BaseBindFragment
import cashtracker.alibard.tm.com.cashtracker.BR
import cashtracker.alibard.tm.com.cashtracker.R
import cashtracker.alibard.tm.com.cashtracker.databinding.SpendingFragmentBinding
import cashtracker.alibard.tm.com.db.entities.SpendingType
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.bottom_sheet.*
import kotlinx.android.synthetic.main.spending_fragment.*
import javax.inject.Inject
import android.support.design.widget.BottomSheetBehavior




class AddSpendingFragment : BaseBindFragment<SpendingFragmentBinding, SpendingVIewModel>(),
        SpendingNavigator {
    override fun fillData(it: List<SpendingType>) {

        typeSpending.adapter = ArrayAdapter(context, R.layout.spinner_item, toListFromModel(it))
    }

    private fun toListFromModel(it: List<SpendingType>): Array<String> {
        val namesList: MutableList<String> = mutableListOf()
        it.forEach {
            namesList.add(it.name)
        }
        return namesList.toTypedArray()
    }


    override fun onCreteViewModel(): SpendingVIewModel = ViewModelProviders.of(activity, viewModelFactory).get(SpendingVIewModel::class.java)

    override val layoutId: Int
        get() = R.layout.spending_fragment

    override fun getBindingVariable(): Int = BR.spendingModel


    override fun onSuccess() {
        activity.onBackPressed()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var spendingModel: SpendingVIewModel

    companion object {
        fun newInstance(): AddSpendingFragment = AddSpendingFragment()
        val TAG = "AddSpendingFragment"
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        spendingModel.navigator = this
        spendingModel.fillData()

    }


    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.save_spending, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.saveAction -> {
                spendingModel.saveSpending(
                        price.text.toString(),
                        currency.text.toString(),
                        typeSpending.selectedItem.toString(),
                        notis.text.toString()
                )
            }
        }
        return super.onOptionsItemSelected(item)
    }

}