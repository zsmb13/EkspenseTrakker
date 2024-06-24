import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject
import ui.enterT
import ui.exitT
import ui.popEnterT
import ui.popExitT

@Composable
@Preview
fun App() {
    MaterialTheme {
        // TODO remove hardcoded init
        DemoInitializer()

        val navController = rememberNavController()
        NavHost(
            navController,
            startDestination = "home",
            enterTransition = enterT,
            exitTransition = exitT,
            popEnterTransition = popEnterT,
            popExitTransition = popExitT
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
