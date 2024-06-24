import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class AddViewModel(
    private val expenseDao: ExpenseDao,
    private val dataStore: DataStore<Preferences>,
) : ViewModel() {
    val people: StateFlow<List<Person>> = expenseDao.getAllPeople()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val personId = MutableStateFlow<String?>(null)

    val recordCreated = MutableStateFlow(false)

    fun initialize() {
        viewModelScope.launch {
            personId.update { dataStore.data.first()[lastUsedPersonId] }
        }
    }

    fun selectPerson(personId: String) {
        println("Selected $personId")
        this.personId.update { personId }
    }

    fun createRecord(amount: String) {
        val expense = Expense(randomUUID(), personId.value!!, amount.toInt())

        viewModelScope.launch {
            dataStore.edit { it[lastUsedPersonId] = expense.paidByPersonId }
            expenseDao.insert(expense)
            recordCreated.update { true }
        }
    }
}
