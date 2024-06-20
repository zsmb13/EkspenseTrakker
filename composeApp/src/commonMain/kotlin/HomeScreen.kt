import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI


class HomeViewModel(private val expenseDao: ExpenseDao) : ViewModel() {
    val people: StateFlow<List<Person>> = expenseDao.getAllPeople()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val expenses: StateFlow<List<ExpenseWithPerson>> = expenseDao.getAllWithPeople()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
}

@OptIn(KoinExperimentalAPI::class)
@Composable
fun HomeScreen(onAddNewRecord: () -> Unit) {
    val homeViewModel = koinViewModel<HomeViewModel>()

    val people by homeViewModel.people.collectAsState()
    val expenses by homeViewModel.expenses.collectAsState()

    val sum = remember(expenses) { expenses.sumOf { it.expense.amount } }

    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        LazyRow {
            items(people, key = { it.id }) { person ->
                PersonItem(person)
            }
        }

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
