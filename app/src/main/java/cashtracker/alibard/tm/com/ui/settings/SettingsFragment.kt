package cashtracker.alibard.tm.com.ui.settings

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cashtracker.alibard.tm.com.cashtracker.R


class SettingsFragment : Fragment() {
    companion object {
        fun newInstance():SettingsFragment = SettingsFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.settings_fragment,container,false)
    }
}