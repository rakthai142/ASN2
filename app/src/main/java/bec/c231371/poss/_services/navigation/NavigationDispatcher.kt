package bec.c231371.poss._services.navigation

import androidx.navigation.NavDirections
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class NavigationDispatcher @Inject constructor() {

    private val _navRequests = SingleLiveEvent<NavDirections>()
    val navRequests: SingleLiveEvent<NavDirections> = _navRequests

}
