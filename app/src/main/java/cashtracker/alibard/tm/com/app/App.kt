package cashtracker.alibard.tm.com.app

import android.app.Activity
import android.app.Application
import android.util.Log
import cashtracker.alibard.tm.com.app.di.DaggerAppComponent
import cashtracker.alibard.tm.com.init_settings.BaseType
import cashtracker.alibard.tm.com.repository.local.LocalRepository
import com.google.firebase.FirebaseApp
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject


class App : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector

    @Inject
    lateinit var repository: LocalRepository

    override fun onCreate() {
        super.onCreate()
        Log.d("baseType", "" + BaseType.values().size)
        DaggerAppComponent
                .builder()
                .context(this)
                .build()
                .inject(this)
        FirebaseApp.initializeApp(this)

        Initializer.initialize(repository)
    }


}