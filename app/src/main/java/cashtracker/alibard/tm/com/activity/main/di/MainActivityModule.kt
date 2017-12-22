package cashtracker.alibard.tm.com.activity.main.di

import android.arch.lifecycle.ViewModel
import cashtracker.alibard.tm.com.activity.main.MainViewModel
import cashtracker.alibard.tm.com.app.di.ViewModelKey
import cashtracker.alibard.tm.com.ui.home.MainFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap


@Module
internal abstract class MainActivityModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): MainFragment
}