package cashtracker.alibard.tm.com.ui.add_spending

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cashtracker.alibard.tm.com.cashtracker.R


class AddSpendingFragment : Fragment() {
    companion object {
        fun newInstance(): AddSpendingFragment = AddSpendingFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.spending_fragment,container,false)
    }
}