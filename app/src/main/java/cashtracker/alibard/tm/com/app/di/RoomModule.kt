package cashtracker.alibard.tm.com.app.di

import android.app.Application
import android.arch.persistence.room.Room
import cashtracker.alibard.tm.com.repository.database.CashTrackarBase
import cashtracker.alibard.tm.com.repository.database.SpendingDao
import cashtracker.alibard.tm.com.repository.local.LocalImpl
import cashtracker.alibard.tm.com.repository.local.LocalRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Singleton
@Module
class RoomModule(application: Application) {
    private var roomDatabase: CashTrackarBase = Room.databaseBuilder(application.applicationContext, CashTrackarBase::class.java, "cash-db").allowMainThreadQueries().build()

    @Singleton
    @Provides
    fun provideDB(): CashTrackarBase = roomDatabase


    @Singleton
    @Provides
    fun provideDao(): SpendingDao = roomDatabase.spendingDao()

    @Singleton
    @Provides
    fun provideLocalRepository(dao: SpendingDao): LocalRepository {
        return LocalImpl(dao)
    }
}