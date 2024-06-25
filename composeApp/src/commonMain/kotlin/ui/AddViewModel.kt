import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class AddViewModel(
    private val expenseDao: ExpenseDao,
) : ViewModel() {
    val people: StateFlow<List<Person>> = expenseDao.getAllPeople()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val personId = MutableStateFlow<String?>(null)

    val recordCreated = MutableStateFlow(false)

    fun selectPerson(personId: String) {
        this.personId.update { personId }
    }

    fun createRecord(amount: Int, description: String) {
        val expense = Expense(
            id = randomUUID(),
            paidByPersonId = requireNotNull(personId.value),
            amount = amount,
            description = description,
        )

        viewModelScope.launch {
            expenseDao.insert(expense)
            recordCreated.update { true }
        }
    }
}
