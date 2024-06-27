package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ekspensetrakker.composeapp.generated.resources.Res
import ekspensetrakker.composeapp.generated.resources.add_expense
import ekspensetrakker.composeapp.generated.resources.total
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun HomeScreen(
    onAddNewRecord: () -> Unit,
    viewModel: HomeViewModel = koinViewModel(),
) {
    val expenses by viewModel.expenses.collectAsStateWithLifecycle()

    val total = remember(expenses) { expenses.sumOf { it.expense.amount } }

    Column(Modifier.fillMaxSize().padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        LazyColumn(Modifier.fillMaxWidth().weight(1f)) {
            items(expenses) {
                ExpenseItem(expense = it.expense, paidBy = it.paidBy)
            }
        }
        Spacer(
            Modifier.padding(5.dp).height(2.dp).fillMaxWidth().background(MaterialTheme.colors.primary)
        )
        Text(stringResource(Res.string.total, total), fontSize = 24.sp, modifier = Modifier.padding(16.dp).clickable {
            viewModel.toggleTheme()
        })
        Button(onClick = { onAddNewRecord() }) {
            Text(stringResource(Res.string.add_expense))
        }
    }
}
