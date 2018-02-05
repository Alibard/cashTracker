package cashtracker.alibard.tm.com.activity.additional_activity.di

import cashtracker.alibard.tm.com.scope.FragmentScope
import cashtracker.alibard.tm.com.ui.add_spending.AddSpendingFragment
import cashtracker.alibard.tm.com.ui.add_spending.di.SpendingModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class AdditionalModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = arrayOf(SpendingModule::class))
    abstract fun spendingFragmentInject():AddSpendingFragment

}