package cashtracker.alibard.tm.com.ui.home.di

import android.arch.lifecycle.ViewModel
import cashtracker.alibard.tm.com.app.di.ViewModelKey
import cashtracker.alibard.tm.com.ui.home.MainFragmentModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainFragmentModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainFragmentModel::class)
    abstract fun bindMainViewModel(viewModel: MainFragmentModel): ViewModel
}