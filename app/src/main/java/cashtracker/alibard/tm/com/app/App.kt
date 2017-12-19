package cashtracker.alibard.tm.com.app

import android.app.Application
import android.content.Context
import cashtracker.alibard.tm.com.app.di.AppComponent
import cashtracker.alibard.tm.com.app.di.DaggerAppComponent
import com.google.firebase.FirebaseApp


class App : Application(){
    lateinit var appComponent : AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().build()
        appComponent.inject(this)
        FirebaseApp.initializeApp(this)
    }
    companion object {
        fun getComponent(ctx: Context) = (ctx.applicationContext as App).appComponent
    }
}