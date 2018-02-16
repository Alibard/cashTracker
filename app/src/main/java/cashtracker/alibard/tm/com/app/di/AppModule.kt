package cashtracker.alibard.tm.com.app.di

import android.arch.lifecycle.ViewModelProvider
import cashtracker.alibard.tm.com.activity.additional_activity.AdditionalActivity
import cashtracker.alibard.tm.com.activity.additional_activity.di.AdditionalModule
import cashtracker.alibard.tm.com.activity.main.MainActivity
import cashtracker.alibard.tm.com.activity.main.di.MainModule
import cashtracker.alibard.tm.com.scope.ActivityScope
import cashtracker.alibard.tm.com.utils.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Module(includes = arrayOf(AndroidSupportInjectionModule::class))
abstract class AppModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(AdditionalModule::class))
    abstract fun additiobnalActivityInjectot(): AdditionalActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(MainModule::class))
    abstract fun mainActivityInjectot(): MainActivity
}