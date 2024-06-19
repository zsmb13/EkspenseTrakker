import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.util.TableInfo
import com.benasher44.uuid.UUID
import com.benasher44.uuid.uuid4
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject

@Composable
@Preview
fun App() {
    MaterialTheme {
        val expenseDao = koinInject<ExpenseDao>()
        val expenses: List<ExpenseWithPerson> by expenseDao.getAllWithPeople().collectAsState(emptyList())
        val people: List<Person> by expenseDao.getAllPeople().collectAsState(emptyList())

        // TODO remove hardcoded init
        LaunchedEffect(Unit) {
            expenseDao.deleteAll()

            val people = listOf(
                Person(uuid4().toString(), "Alice"),
                Person(uuid4().toString(), "Bob"),
                Person(uuid4().toString(), "Charlie"),
                Person(uuid4().toString(), "David"),
                Person(uuid4().toString(), "Eve"),
            )
            people.forEach {
                expenseDao.insert(it)
            }

            listOf(
                Expense(uuid4().toString(), people[1-1].id, 11),
                Expense(uuid4().toString(), people[2-1].id, 22),
                Expense(uuid4().toString(), people[3-1].id, 33),
                Expense(uuid4().toString(), people[4-1].id, 44),
                Expense(uuid4().toString(), people[5-1].id, 55),
            ).forEach { expenseDao.insert(it) }
        }

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
        }
    }
}

@Composable
fun ExpenseItem(expense: ExpenseWithPerson) {
    Row(Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
        Icon(Icons.Default.ShoppingCart, null)
        Spacer(Modifier.size(16.dp))
        Column(Modifier.weight(1f)) {
            Text("KotlinConf catering", fontSize = 20.sp)
            Text("paid by ${expense.paidBy.name}")
        }
        Text("$${expense.expense.amount}")
    }
}

@Composable
fun PersonItem(person: Person) {
    val color = remember(person) { Color(120, 50, person.name.hashCode()) }

    Column(Modifier.padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Box(Modifier.clip(CircleShape).background(color).size(48.dp)) { }
        Spacer(Modifier.size(8.dp))
        Text(person.name)
    }
}
