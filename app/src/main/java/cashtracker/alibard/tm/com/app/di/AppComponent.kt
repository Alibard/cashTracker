package cashtracker.alibard.tm.com.app.di

import android.content.Context
import cashtracker.alibard.tm.com.app.App
import cashtracker.alibard.tm.com.repository.local.LocalRepository
import cashtracker.alibard.tm.com.repository.remout.RemoutRepository
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(app: App)


    fun context(): Context
    fun provideLocalRep(): LocalRepository

}