import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.room.RoomDatabase
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(databaseBuilder: RoomDatabase.Builder<ExpenseDatabase>) {
    MaterialTheme {
        val database = remember { databaseBuilder.getRoomDatabase() }
        val expenseDao = remember(database) { database.expenseDao() }

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