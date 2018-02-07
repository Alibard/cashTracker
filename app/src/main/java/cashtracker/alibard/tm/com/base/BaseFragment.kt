package cashtracker.alibard.tm.com.base

import android.app.AlertDialog
import android.databinding.ViewDataBinding
import android.support.v4.app.Fragment
import android.widget.Toast
import cashtracker.alibard.tm.com.cashtracker.R


abstract class BaseFragment : Fragment(), BaseNavigator {
    private var progressDialog: AlertDialog? = null
    override fun onError(it: Throwable) {
        Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoad() {
        if (progressDialog != null && progressDialog!!.isShowing) {
            return
        }
        progressDialog = AlertDialog.Builder(context, R.style.AppTheme_ProgressDialog)
                .setView(R.layout.dialog_progress)
                .show()
    }

    override fun hideLoad() {
        progressDialog?.dismiss()
    }
}