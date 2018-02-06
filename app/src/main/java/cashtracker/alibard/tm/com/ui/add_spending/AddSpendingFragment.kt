package cashtracker.alibard.tm.com.ui.add_spending

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.*
import android.widget.ArrayAdapter
import cashtracker.alibard.tm.com.base.BaseFragment
import cashtracker.alibard.tm.com.cashtracker.R
import cashtracker.alibard.tm.com.utils.enums.CurrensyType
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.spending_fragment.*
import javax.inject.Inject


class AddSpendingFragment : BaseFragment(),
        SpendingNavigator{
    override fun onSuccess() {
        Log.d("onSuccess", "test")
    }

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject lateinit var spendingModel: SpendingVIewModel

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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.spending_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        spendingModel = ViewModelProviders.of(activity, viewModelFactory).get(SpendingVIewModel::class.java)
        spendingModel.navigator = this
        typeSpending.adapter = ArrayAdapter(context, R.layout.spinner_item, CurrensyType.values())

    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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