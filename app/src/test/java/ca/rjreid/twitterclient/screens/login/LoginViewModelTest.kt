package ca.rjreid.twitterclient.screens.login

import ca.rjreid.twitterclient.BaseTest
import ca.rjreid.twitterclient.screens.list.ListActivity
import com.jraska.livedata.test
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test

class LoginViewModelTest : BaseTest() {
    companion object {
        private const val VALID_USERNAME = "user123"
        private const val VALID_PASSWORD = "pass123"
        private const val INVALID_USERNAME = "invalid-user123"
        private const val INVALID_PASSWORD = "invalid-pass123"
    }
    //region Variables
    private lateinit var viewModel: LoginViewModel
    //endregion

    //region Setup
    @Before
    fun setup() {
        viewModel = LoginViewModel(repositoryDelegate)

        whenever(repositoryDelegate.login(VALID_USERNAME, VALID_PASSWORD)).thenReturn(Completable.complete())
        whenever(repositoryDelegate.login(INVALID_USERNAME, INVALID_PASSWORD)).thenReturn(Completable.error(Throwable("Login Failed")))
    }
    //endregion

    //region Tests
    @Test
    fun `GIVEN null input WHEN login clicked THEN set error livedata`() {
        viewModel.usernameEditTextValue.value = ""
        viewModel.passwordEditTextValue.value = ""

        viewModel.onLoginClick()

        viewModel
            .usernameBlankError
            .test()
            .assertHasValue()
            .assertValue(true)

        viewModel
            .passwordBlankError
            .test()
            .assertHasValue()
            .assertValue(true)
    }

    @Test
    fun `GIVEN null username WHEN login clicked THEN set username error livedata`() {
        viewModel.usernameEditTextValue.value = ""
        viewModel.passwordEditTextValue.value = "pass123"

        viewModel.onLoginClick()

        viewModel
            .usernameBlankError
            .test()
            .assertHasValue()
            .assertValue(true)

        viewModel
            .passwordBlankError
            .test()
            .assertHasValue()
            .assertValue(false)

        viewModel
            .loginFailedError
            .test()
            .assertNoValue()
    }

    @Test
    fun `GIVEN null password WHEN login clicked THEN set password error livedata`() {
        viewModel.usernameEditTextValue.value = "user123"
        viewModel.passwordEditTextValue.value = ""

        viewModel.onLoginClick()

        viewModel
            .usernameBlankError
            .test()
            .assertHasValue()
            .assertValue(false)

        viewModel
            .passwordBlankError
            .test()
            .assertHasValue()
            .assertValue(true)

        viewModel
            .loginFailedError
            .test()
            .assertNoValue()
    }

    @Test
    fun `GIVEN valid input WHEN login clicked THEN set no error`() {
        viewModel.usernameEditTextValue.value = VALID_USERNAME
        viewModel.passwordEditTextValue.value = VALID_PASSWORD

        viewModel.onLoginClick()

        viewModel
            .loginFailedError
            .test()
            .assertValue {
                !it.peekValue()
            }
    }

    @Test
    fun `GIVEN valid input WHEN login clicked THEN start list activity`() {
        viewModel.usernameEditTextValue.value = VALID_USERNAME
        viewModel.passwordEditTextValue.value = VALID_PASSWORD

        viewModel.onLoginClick()

        viewModel
            .activityToStart
            .test()
            .assertValue {
                it.peekValue().activity == ListActivity::class
            }
    }

    @Test
    fun `GIVEN valid input WHEN login clicked THEN finish`() {
        viewModel.usernameEditTextValue.value = VALID_USERNAME
        viewModel.passwordEditTextValue.value = VALID_PASSWORD

        viewModel.onLoginClick()

        viewModel
            .shouldFinish
            .test()
            .assertValue {
                it
            }
    }

    @Test
    fun `GIVEN valid input password WHEN login clicked THEN set error`() {
        viewModel.usernameEditTextValue.value = INVALID_USERNAME
        viewModel.passwordEditTextValue.value = INVALID_PASSWORD

        viewModel.onLoginClick()

        viewModel
            .loginFailedError
            .test()
            .assertValue {
                it.peekValue()
            }
    }

    @Test
    fun `GIVEN valid input password WHEN login clicked THEN do not start activity`() {
        viewModel.usernameEditTextValue.value = INVALID_USERNAME
        viewModel.passwordEditTextValue.value = INVALID_PASSWORD

        viewModel.onLoginClick()

        viewModel
            .activityToStart
            .test()
            .assertNoValue()
    }

    @Test
    fun `GIVEN valid input password WHEN login clicked THEN do not finish`() {
        viewModel.usernameEditTextValue.value = INVALID_USERNAME
        viewModel.passwordEditTextValue.value = INVALID_PASSWORD

        viewModel.onLoginClick()

        viewModel
            .shouldFinish
            .test()
            .assertNoValue()
    }
    //endregion
}







