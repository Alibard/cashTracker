package cashtracker.alibard.tm.com.app

import android.app.Activity
import android.app.Application
import android.content.Context
import cashtracker.alibard.tm.com.app.di.AppComponent
import cashtracker.alibard.tm.com.app.di.AppModule
import cashtracker.alibard.tm.com.app.di.DaggerAppComponent
import cashtracker.alibard.tm.com.app.di.RoomModule
import com.google.firebase.FirebaseApp
import dagger.android.AndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject
import dagger.android.DispatchingAndroidInjector




class App : Application(), HasActivityInjector{
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }

    lateinit var appComponent : AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
                .roomModule(RoomModule(this))
                .appModule(AppModule(this))
                .build()
        appComponent.inject(this)
        FirebaseApp.initializeApp(this)
    }

    companion object {
        fun getComponent(ctx: Context) = (ctx.applicationContext as App).appComponent
    }
}