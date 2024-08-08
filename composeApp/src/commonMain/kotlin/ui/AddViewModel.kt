package ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.Expense
import data.ExpenseDao
import data.Person
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import randomUUID
import kotlin.random.Random

class AddViewModel(
    private val expenseDao: ExpenseDao,
) : ViewModel() {
    val people: StateFlow<List<Person>> = expenseDao.getAllPeople()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _recordCreated = MutableStateFlow(false)
    val recordCreated: StateFlow<Boolean> = _recordCreated

    fun createRecord(amount: Int, description: String, personId: Long) {
        val expense = Expense(
            paidByPersonId = personId,
            amount = amount,
            description = description,
        )

        viewModelScope.launch {
            expenseDao.insert(expense)
            _recordCreated.update { true }
        }
    }
}
