package ui

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.ExpenseDao
import data.ExpenseWithPerson
import data.darkThemeKey
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    private val expenseDao: ExpenseDao,
    private val dataStore: DataStore<Preferences>,
) : ViewModel() {
    val expenses: StateFlow<List<ExpenseWithPerson>> = expenseDao
        .getAllWithPeople()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun toggleTheme() {
        viewModelScope.launch {
            dataStore.edit {
                it[darkThemeKey] = it[darkThemeKey]?.not() ?: true
            }
        }
    }
}
