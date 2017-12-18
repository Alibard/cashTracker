package cashtracker.alibard.tm.com.ui.add_spending

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import android.widget.ArrayAdapter
import cashtracker.alibard.tm.com.cashtracker.R
import cashtracker.alibard.tm.com.utils.enums.CurrensyType
import kotlinx.android.synthetic.main.spending_fragment.*


class AddSpendingFragment : Fragment() {
    companion object {
        fun newInstance(): AddSpendingFragment = AddSpendingFragment()
        val TAG = "AddSpendingFragment"
    }

    val  spendingModel by lazy {
        ViewModelProviders.of(this).get(SpendingVIewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.spending_fragment, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        typeSpending.adapter = ArrayAdapter(context, R.layout.spinner_item, CurrensyType.values())
    }


    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.save_spending, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.saveAction -> {
            }
        }
        return super.onOptionsItemSelected(item)
    }

}