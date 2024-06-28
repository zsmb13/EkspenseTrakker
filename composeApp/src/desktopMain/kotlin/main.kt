import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import ekspensetrakker.composeapp.generated.resources.Res
import ekspensetrakker.composeapp.generated.resources.ipad
import org.jetbrains.compose.resources.imageResource


fun main() {
    initKoin(
        databaseBuilder = createDatabaseBuilder(),
    )

    application {
        val state = rememberWindowState()
        var squashed by remember { mutableStateOf(false) }

        val zeroHeight by derivedStateOf {
            state.size.height <= 30.dp
        }
        LaunchedEffect(zeroHeight) {
            if (zeroHeight) squashed = true
        }

        Window(
            onCloseRequest = ::exitApplication,
            state = state,
            title = when {
                zeroHeight -> "-------"
                squashed -> "iPad Pro 2024 ad"
                else -> "Expense Tracker!"
            }
        ) {
            Box(contentAlignment = Alignment.BottomCenter, modifier = Modifier.fillMaxSize()) {
                if (squashed) {
                    Image(imageResource(Res.drawable.ipad), "iPad Pro 2024")
                } else {
                    App()
                }
            }
        }
    }
}
