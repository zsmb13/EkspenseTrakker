import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import data.DemoInitializer
import kotlinx.serialization.Serializable
import ui.*

@Serializable
data object HomeRoute

@Serializable
data object AddRoute

@Composable
fun App() {
    MaterialTheme {
        // TODO remove hardcoded init
        DemoInitializer()

        Surface(Modifier.fillMaxSize()) {
            val navController = rememberNavController()
            NavHost(
                navController,
                startDestination = HomeRoute,
                enterTransition = enterT,
                exitTransition = exitT,
                popEnterTransition = popEnterT,
                popExitTransition = popExitT,
            ) {
                composable<HomeRoute> {
                    HomeScreen(onAddNewRecord = { navController.navigate(AddRoute) })
                }
                composable<AddRoute> {
                    AddScreen(onDone = { navController.navigateUp() })
                }
            }
        }
    }
}
