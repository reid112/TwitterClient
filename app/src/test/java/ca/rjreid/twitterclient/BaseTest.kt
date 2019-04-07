package ca.rjreid.twitterclient

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import ca.rjreid.twitterclient.data.DataManagerDelegate
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import org.mockito.Mock
import org.mockito.MockitoAnnotations

abstract class BaseTest {
    //region Rules
    @get:Rule var schedulerRule = SchedulerRule()
    @get:Rule var rule: TestRule = InstantTaskExecutorRule() // LiveData test rule
    //endregion

    //region Variables
    @Mock lateinit var dataManagerDelegate: DataManagerDelegate
    //endregion

    //region Setup
    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
    }
    //endregion
}

//region Scheduler Rule
class SchedulerRule : TestRule {
    override fun apply(base: Statement?, description: Description?): Statement {
        return object : Statement() {
            override fun evaluate() {
                RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
                RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
                RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
                RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
                RxAndroidPlugins.setMainThreadSchedulerHandler { Schedulers.trampoline() }

                try {
                    base?.evaluate()
                } finally {
                    RxJavaPlugins.reset()
                    RxAndroidPlugins.reset()
                }
            }
        }
    }
}
//endregion