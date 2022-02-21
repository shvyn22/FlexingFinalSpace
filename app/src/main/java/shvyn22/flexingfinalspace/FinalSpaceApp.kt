package shvyn22.flexingfinalspace

import android.app.Application
import shvyn22.flexingfinalspace.di.component.DaggerSingletonComponent
import shvyn22.flexingfinalspace.di.component.SingletonComponent

class FinalSpaceApp: Application() {

    lateinit var singletonComponent: SingletonComponent

    override fun onCreate() {
        super.onCreate()

        singletonComponent = DaggerSingletonComponent.factory().create(this)
    }
}