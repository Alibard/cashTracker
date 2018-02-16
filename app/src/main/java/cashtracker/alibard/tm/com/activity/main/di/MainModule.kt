package cashtracker.alibard.tm.com.activity.main.di

import cashtracker.alibard.tm.com.scope.FragmentScope
import cashtracker.alibard.tm.com.test.di.TestModule
import cashtracker.alibard.tm.com.ui.add_spending.AddSpendingFragment
import cashtracker.alibard.tm.com.ui.add_spending.di.SpendingModule
import cashtracker.alibard.tm.com.ui.home.MainFragment
import cashtracker.alibard.tm.com.ui.home.di.MainFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = arrayOf(MainFragmentModule::class))
    abstract fun mainFragmentInject(): MainFragment
}