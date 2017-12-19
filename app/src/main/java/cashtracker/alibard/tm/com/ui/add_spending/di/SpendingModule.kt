package cashtracker.alibard.tm.com.ui.add_spending.di

import cashtracker.alibard.tm.com.repository.local.LocalRepository
import cashtracker.alibard.tm.com.scope.FragmentScope
import cashtracker.alibard.tm.com.ui.add_spending.SpendingVIewModel
import dagger.Module
import dagger.Provides


@Module
class SpendingModule {

    @Provides
    @FragmentScope
    fun provideViewModel(rep: LocalRepository): SpendingVIewModel {
        return SpendingVIewModel(rep)
    }
}