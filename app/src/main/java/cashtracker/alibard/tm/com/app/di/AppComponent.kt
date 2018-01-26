package cashtracker.alibard.tm.com.app.di

import android.content.Context
import android.net.Uri
import cashtracker.alibard.tm.com.app.App
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component (modules = arrayOf(AppModule::class,RoomModule::class))
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponent
    }
    fun inject(app: App)

}