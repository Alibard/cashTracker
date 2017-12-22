package cashtracker.alibard.tm.com.app.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import cashtracker.alibard.tm.com.activity.main.MainActivity
import cashtracker.alibard.tm.com.activity.main.MainViewModel
import cashtracker.alibard.tm.com.activity.main.di.MainActivityModule
import cashtracker.alibard.tm.com.utils.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap


@Module
internal abstract class UiModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory


    @ContributesAndroidInjector(modules = arrayOf(MainActivityModule::class))
    internal abstract fun contributeMainActivity(): MainActivity
}