package cashtracker.alibard.tm.com.base

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import io.reactivex.disposables.CompositeDisposable


abstract class BaseViewModel<N> : ViewModel() {

    var navigator: N? = null

}
