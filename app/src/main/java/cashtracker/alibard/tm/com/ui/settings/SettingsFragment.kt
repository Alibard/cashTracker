package cashtracker.alibard.tm.com.ui.settings

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cashtracker.alibard.tm.com.cashtracker.R
import cashtracker.alibard.tm.com.cashtracker.databinding.MainActivityBinding
import cashtracker.alibard.tm.com.cashtracker.databinding.SettingsFragmentBinding


class SettingsFragment : Fragment() {
    companion object {
        fun newInstance(): SettingsFragment = SettingsFragment()
    }

    private val settingModel by lazy {
        ViewModelProviders.of(this).get(SettingViewModel::class.java)
    }
    lateinit var settingsBindView: SettingsFragmentBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.settings_fragment, container, false)
        settingsBindView = SettingsFragmentBinding.bind(view).apply {
            user = settingModel
        }

        return view
    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        settingModel.fillSettings()

    }

}