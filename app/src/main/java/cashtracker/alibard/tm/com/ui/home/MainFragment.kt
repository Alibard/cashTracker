package cashtracker.alibard.tm.com.ui.home

import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cashtracker.alibard.tm.com.cashtracker.R
import cashtracker.alibard.tm.com.model.TestList
import cashtracker.alibard.tm.com.ui.home.adapter.MainAdapter
import com.airbnb.epoxy.EpoxyRecyclerView


class MainFragment : Fragment() {
    companion object {
        fun newInstance(): MainFragment = MainFragment()
    }

    private val adapter by lazy {
        MainAdapter(ArrayList<TestList>())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.main_fragment, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<EpoxyRecyclerView>(R.id.recycler_view)
        val someList: MutableList<TestList> = ArrayList()
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        someList.add(TestList("one"))
        someList.add(TestList("duo"))
        someList.add(TestList("trio"))
        adapter.fillmodels(someList.toList())
    }

}

