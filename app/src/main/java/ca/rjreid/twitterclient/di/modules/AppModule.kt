package ca.rjreid.twitterclient.di.modules

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class AppModule {
    //expose Application as an injectable context
    @Binds
    @Singleton
    internal abstract fun bindContext(application: Application): Context
}