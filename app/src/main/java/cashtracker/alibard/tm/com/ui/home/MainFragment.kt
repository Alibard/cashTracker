package cashtracker.alibard.tm.com.ui.home

import android.content.Intent
import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cashtracker.alibard.tm.com.activity.additional_activity.AdditionalActivity
import cashtracker.alibard.tm.com.activity.main.MainActivity
import cashtracker.alibard.tm.com.cashtracker.R
import cashtracker.alibard.tm.com.ui.add_spending.AddSpendingFragment
import cashtracker.alibard.tm.com.ui.home.adapter.MainAdapter
import cashtracker.alibard.tm.com.utils.extention.replaceFragmentInActivity
import com.airbnb.epoxy.EpoxyRecyclerView
import kotlinx.android.synthetic.main.main_fragment.*


class MainFragment : Fragment() {
    companion object {
        fun newInstance(): MainFragment = MainFragment()
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.main_fragment, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<EpoxyRecyclerView>(R.id.recycler_view)
//        val someList: MutableList<TestList> = ArrayList()
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
//        recyclerView.adapter = adapter
//        someList.add(TestList("one"))
//        someList.add(TestList("duo"))
//        someList.add(TestList("trio"))
//        adapter.fillmodels(someList.toList())
        fab.setOnClickListener{
            val intent = Intent(activity,AdditionalActivity::class.java)
            intent.putExtra("fragment",AddSpendingFragment.TAG)
            startActivity(intent)
        }
    }

}

