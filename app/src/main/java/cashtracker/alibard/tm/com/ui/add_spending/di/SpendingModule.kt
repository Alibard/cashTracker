package cashtracker.alibard.tm.com.ui.add_spending.di

import android.arch.lifecycle.ViewModel
import cashtracker.alibard.tm.com.app.di.ViewModelKey
import cashtracker.alibard.tm.com.repository.local.LocalImpl
import cashtracker.alibard.tm.com.repository.local.LocalRepository
import cashtracker.alibard.tm.com.scope.FragmentScope
import cashtracker.alibard.tm.com.ui.add_spending.SpendingVIewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SpendingModule {
    @Binds
    @IntoMap
    @ViewModelKey(SpendingVIewModel::class)
    abstract fun bindMainViewModel(viewModel: SpendingVIewModel): ViewModel
}