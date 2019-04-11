package ca.rjreid.twitterclient.di.modules

import ca.rjreid.twitterclient.di.scopes.ActivityScope
import ca.rjreid.twitterclient.screens.list.ListActivity
import ca.rjreid.twitterclient.screens.list.ListModule
import ca.rjreid.twitterclient.screens.login.LoginActivity
import ca.rjreid.twitterclient.screens.login.LoginModule
import ca.rjreid.twitterclient.screens.splash.SplashScreenActivity
import ca.rjreid.twitterclient.screens.splash.SplashScreenModule
import ca.rjreid.twitterclient.screens.tweet.TweetActivity
import ca.rjreid.twitterclient.screens.tweet.TweetModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [SplashScreenModule::class])
    internal abstract fun bindSplashScreenActivity(): SplashScreenActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [LoginModule::class])
    internal abstract fun bindLoginActivity(): LoginActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [ListModule::class])
    internal abstract fun bindListActivity(): ListActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [TweetModule::class])
    internal abstract fun bindTweetActivity(): TweetActivity
}