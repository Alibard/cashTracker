package cashtracker.alibard.tm.com.app.di

import cashtracker.alibard.tm.com.app.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(
        AndroidSupportInjectionModule::class,
        UiModule::class))
interface AppComponent : AndroidInjector<App>  {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder
        fun build(): AppComponent
    }
    override fun inject(app: App)
}