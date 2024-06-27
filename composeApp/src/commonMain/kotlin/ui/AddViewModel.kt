package ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.Expense
import data.ExpenseDao
import data.Person
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import randomUUID

class AddViewModel(
    private val expenseDao: ExpenseDao,
) : ViewModel() {
    val people: StateFlow<List<Person>> = expenseDao.getAllPeople()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _personId = MutableStateFlow<String?>(null)
    val personId: StateFlow<String?> = _personId

    private val _recordCreated = MutableStateFlow(false)
    val recordCreated: StateFlow<Boolean> = _recordCreated

    fun selectPerson(personId: String) {
        this._personId.update { personId }
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
            _recordCreated.update { true }
        }
    }
}
