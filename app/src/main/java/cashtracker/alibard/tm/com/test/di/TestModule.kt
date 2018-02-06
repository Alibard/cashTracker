package cashtracker.alibard.tm.com.test.di

import android.arch.lifecycle.ViewModel
import cashtracker.alibard.tm.com.app.di.ViewModelKey
import cashtracker.alibard.tm.com.test.TestViewModel
import cashtracker.alibard.tm.com.ui.add_spending.SpendingVIewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class TestModule {
//    @Binds
//    @IntoMap
//    @ViewModelKey(TestViewModel::class)
//    abstract fun bindMainViewModel(viewModel: TestViewModel): ViewModel
}