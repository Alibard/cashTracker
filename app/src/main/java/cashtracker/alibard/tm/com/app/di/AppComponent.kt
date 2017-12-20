package cashtracker.alibard.tm.com.app.di

import android.content.Context
import cashtracker.alibard.tm.com.app.App
import cashtracker.alibard.tm.com.repository.database.CashTrackarBase
import cashtracker.alibard.tm.com.repository.database.SpendingDao
import cashtracker.alibard.tm.com.repository.local.LocalRepository
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(AppModule::class,RoomModule::class))
interface AppComponent {
    fun inject(app: App)


    fun context(): Context
    fun provideLocalRep(): LocalRepository
    fun provideDao(): SpendingDao
    fun provideDb(): CashTrackarBase
}