import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject

@Composable
@Preview
fun App() {
    MaterialTheme {
        val expenseDao = koinInject<ExpenseDao>()

        // TODO remove hardcoded init
        DemoInitializer(expenseDao)

        val navController = rememberNavController()
        NavHost(
            navController,
            startDestination = "home",
        ) {
            composable("home") {
                HomeScreen(onAddNewRecord = { navController.navigate("add") })
            }
            composable("add") {
                AddScreen(onRecordCreated = { navController.navigateUp() })
            }
        }
    }
}

@Composable
fun ExpenseItem(expense: ExpenseWithPerson) {
    Row(Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
        Icon(Icons.Default.ShoppingCart, null)
        Spacer(Modifier.size(16.dp))
        Column(Modifier.weight(1f)) {
            Text("KotlinConf catering", fontSize = 20.sp)
            Text("paid by ${expense.paidBy.name}")
        }
        Text("$${expense.expense.amount}")
    }
}

@Composable
fun PersonItem(person: Person, modifier: Modifier = Modifier) {
    val color = remember(person) { Color(120, 50, person.name.hashCode()) }

    Column(modifier.padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Box(Modifier.clip(CircleShape).background(color).size(48.dp)) { }
        Spacer(Modifier.size(8.dp))
        Text(person.name)
    }
}
