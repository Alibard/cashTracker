package cashtracker.alibard.tm.com.app

import android.arch.lifecycle.ViewModelProvider
import cashtracker.alibard.tm.com.app.di.DaggerAppComponent
import com.google.firebase.FirebaseApp
import dagger.android.support.DaggerApplication
import javax.inject.Inject


class App : DaggerApplication() {
//    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    override fun applicationInjector() = DaggerAppComponent.builder()
            .application(this)
            .build()


    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)
    }

}