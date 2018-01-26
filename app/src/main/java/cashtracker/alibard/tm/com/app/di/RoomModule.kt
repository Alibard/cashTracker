package cashtracker.alibard.tm.com.app.di

import android.arch.persistence.room.Room
import android.content.Context
import cashtracker.alibard.tm.com.db.CashTrackerDataBase
import cashtracker.alibard.tm.com.repository.local.LocalImpl
import cashtracker.alibard.tm.com.repository.local.LocalRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {
    @Provides
    @Singleton
    fun provideDataBase(context: Context): CashTrackerDataBase {
        return Room.databaseBuilder(context, CashTrackerDataBase::class.java, "cashTrackerDb").fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideLocalRepo(impl: LocalImpl): LocalRepository = impl

}