import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject

@Composable
@Preview
fun App() {
    MaterialTheme {
        val expenseDao = koinInject<ExpenseDao>()

        val expenses: List<Expense> by expenseDao.getAll().collectAsState(emptyList())

        val scope = rememberCoroutineScope()
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = { scope.launch { expenseDao.insert(Expense()) } }) {
                Text("Insert")
            }
            Button(onClick = { scope.launch { expenseDao.deleteAll() } }) {
                Text("Delete all")
            }
            Text(expenses.toString())
        }
    }
}