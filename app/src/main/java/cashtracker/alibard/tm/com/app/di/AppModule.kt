package cashtracker.alibard.tm.com.app.di

import android.app.Application
import android.content.Context
import cashtracker.alibard.tm.com.database.DataBase
import cashtracker.alibard.tm.com.repository.local.LocalImpl
import cashtracker.alibard.tm.com.repository.local.LocalRepository
import cashtracker.alibard.tm.com.repository.remout.RemoutImpl
import cashtracker.alibard.tm.com.repository.remout.RemoutRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule(val application: Application) {

    @Provides
    @Singleton
    fun provideLocalRep(): LocalRepository{
        return  LocalImpl(DataBase.getInstance(application.applicationContext))
    }

//    @Provides
//    @Singleton
//    fun provideRemoutRep(impl: RemoutImpl): RemoutRepository = impl

    @Singleton
    @Provides fun context(): Context = application
}