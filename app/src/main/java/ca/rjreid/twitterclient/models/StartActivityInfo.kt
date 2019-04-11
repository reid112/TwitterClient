package ca.rjreid.twitterclient.models

import android.os.Bundle
import kotlin.reflect.KClass

data class StartActivityInfo(
    val activity: KClass<*>,
    val bundle: Bundle? = null,
    val enterAnimation: ActivityAnimation? = null,
    val exitAnimation: ActivityAnimation? = null
)