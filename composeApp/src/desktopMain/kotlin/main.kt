import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() {
    initKoin(
        databaseBuilder = getDatabaseBuilder(),
        dataStore = createDataStore(),
    )

    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "KotlinProject",
        ) {
            App()
        }
    }
}
