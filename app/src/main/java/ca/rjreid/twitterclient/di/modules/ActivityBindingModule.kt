package ca.rjreid.twitterclient.di.modules

import ca.rjreid.twitterclient.screens.splash.SplashScreenActivity
import ca.rjreid.twitterclient.di.scopes.ActivityScope
import ca.rjreid.twitterclient.screens.splash.SplashScreenModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [SplashScreenModule::class])
    internal abstract fun bindSplashScreenActivity(): SplashScreenActivity
}