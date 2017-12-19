package cashtracker.alibard.tm.com.ui.add_spending.di

import cashtracker.alibard.tm.com.app.di.AppComponent
import cashtracker.alibard.tm.com.scope.FragmentScope
import cashtracker.alibard.tm.com.ui.add_spending.AddSpendingFragment
import dagger.Component


@FragmentScope
@Component (dependencies = arrayOf(AppComponent::class), modules = arrayOf(SpendingModule::class))
interface SpendingComponent {
    fun inject(frag : AddSpendingFragment)
}