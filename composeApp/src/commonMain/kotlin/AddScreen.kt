import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

class AddViewModel(private val expenseDao: ExpenseDao) : ViewModel() {
    val people: StateFlow<List<Person>> = expenseDao.getAllPeople()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val expenses: StateFlow<List<ExpenseWithPerson>> = expenseDao.getAllWithPeople()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val recordCreated = MutableStateFlow(false)

    fun createRecord(expense: Expense) {
        viewModelScope.launch {
            expenseDao.insert(expense)
            recordCreated.update { true }
        }
    }
}

@OptIn(KoinExperimentalAPI::class)
@Composable
fun AddScreen(onRecordCreated: () -> Unit) {
    val addViewModel = koinViewModel<AddViewModel>()

    val recordCreated by addViewModel.recordCreated.collectAsState()
    LaunchedEffect(recordCreated) {
        if (recordCreated) onRecordCreated()
    }

    var personId by remember { mutableStateOf<String?>(null) }
    var amount by remember { mutableStateOf("") }

    val people by addViewModel.people.collectAsState()

    Column(Modifier.fillMaxSize().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        LazyRow {
            items(people) { person ->
                PersonItem(
                    person = person,
                    modifier = Modifier.clickable {
                        personId = if (person.id == personId) null else person.id
                    }.then(
                        if (person.id == personId) {
                            Modifier.border(2.dp, Color.Red)
                        } else {
                            Modifier
                        }
                    )
                )
            }
        }

        TextField(
            value = amount,
            onValueChange = { amount = it.filter { char -> char.isDigit() } },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(), textStyle = LocalTextStyle.current,
        )

        Button(
            onClick = {
                addViewModel.createRecord(Expense(randomUUID(), personId!!, amount.toInt()))
            },
            enabled = personId != null && amount.isNotEmpty()
        ) {
            Text("Add record")
        }
    }
}
