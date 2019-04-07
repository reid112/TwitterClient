package ca.rjreid.twitterclient.screens.splash

import ca.rjreid.twitterclient.BaseTest
import org.junit.Before

class SplashScreenViewModelTest : BaseTest() {

    lateinit var viewModel: SplashScreenViewModel

    @Before
    fun setup() {
        viewModel = SplashScreenViewModel(dataManagerDelegate)
    }
}