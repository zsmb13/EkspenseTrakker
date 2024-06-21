import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(private val expenseDao: ExpenseDao) : ViewModel() {
    val expenses: StateFlow<List<ExpenseWithPerson>> = expenseDao.getAllWithPeople()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
}
