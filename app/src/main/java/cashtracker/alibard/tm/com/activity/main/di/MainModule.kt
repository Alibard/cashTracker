package cashtracker.alibard.tm.com.activity.main.di

import cashtracker.alibard.tm.com.scope.FragmentScope
import cashtracker.alibard.tm.com.test.TestFragment
import cashtracker.alibard.tm.com.test.di.TestModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
 abstract class MainModule {
//    @FragmentScope
//    @ContributesAndroidInjector(modules = arrayOf(TestModule::class))
//    abstract fun testFragmentInject(): TestFragment
}