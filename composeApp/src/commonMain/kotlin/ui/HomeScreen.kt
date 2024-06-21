import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI


@OptIn(KoinExperimentalAPI::class)
@Composable
fun HomeScreen(onAddNewRecord: () -> Unit) {
    val homeViewModel = koinViewModel<HomeViewModel>()

    val expenses by homeViewModel.expenses.collectAsState()

    val sum = remember(expenses) { expenses.sumOf { it.expense.amount } }

    Column(Modifier.fillMaxSize().padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        LazyColumn(Modifier.fillMaxWidth().weight(1f)) {
            items(expenses) { expense ->
                ExpenseItem(expense)
            }
        }

        Text("Total: $$sum", fontSize = 24.sp, modifier = Modifier.padding(16.dp))
        Button(onClick = { onAddNewRecord() }) {
            Text("Add new record")
        }
    }
}
