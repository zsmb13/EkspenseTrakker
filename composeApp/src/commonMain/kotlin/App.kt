import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.map
import org.koin.compose.koinInject
import ui.enterT
import ui.exitT
import ui.popEnterT
import ui.popExitT

@Composable
fun App() {
    val dataStore = koinInject<DataStore<Preferences>>()
    val darkTheme: Boolean by dataStore.data
        .map { it[darkThemeKey] ?: false }
        .collectAsStateWithLifecycle(false)

    MaterialTheme(if (darkTheme) darkColors() else lightColors()) {

        // TODO remove hardcoded init
        DemoInitializer()

        Surface(Modifier.fillMaxSize()) {
            val navController = rememberNavController()
            NavHost(
                navController,
                startDestination = "home",
                enterTransition = enterT,
                exitTransition = exitT,
                popEnterTransition = popEnterT,
                popExitTransition = popExitT,
            ) {
                composable("home") {
                    HomeScreen(onAddNewRecord = { navController.navigate("add") })
                }
                composable("add") {
                    AddScreen(onRecordCreated = { navController.navigateUp() }, onBack = { navController.navigateUp() })
                }
            }
        }
    }
}
