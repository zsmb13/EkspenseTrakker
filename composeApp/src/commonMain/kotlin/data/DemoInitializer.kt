package data

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import ekspensetrakker.composeapp.generated.resources.Res
import ekspensetrakker.composeapp.generated.resources.*
import org.jetbrains.compose.resources.DrawableResource
import org.koin.compose.koinInject

val demoPeople = listOf(
    Person(1, "Alex"),
    Person(2, "Blake"),
    Person(3, "Casey"),
    Person(4, "Drew"),
    Person(5, "Erin"),
)

val demoExpenses = listOf(
    Expense(paidByPersonId = demoPeople.random().id, amount = 129, description = "Business trip"),
    Expense(paidByPersonId = demoPeople.random().id, amount = 12, description = "Kodee Meet & Greet"),
    Expense(paidByPersonId = demoPeople.random().id, amount = 57, description = "Standard Library Card"),
    Expense(paidByPersonId = demoPeople.random().id, amount = 86, description = "droidcon Moon Tickets"),
    Expense(paidByPersonId = demoPeople.random().id, amount = 3, description = "Cat Tax 🐱"),
    Expense(paidByPersonId = demoPeople.random().id, amount = 8, description = "Omea License"),
    Expense(paidByPersonId = demoPeople.random().id, amount = 60, description = "Multiplatform Multivitamins"),
    Expense(paidByPersonId = demoPeople.random().id, amount = 12, description = "Kotlin in Action, 2nd Edition"),
)

private val demoAvatars = listOf(
    Res.drawable.stock1,
    Res.drawable.stock2,
    Res.drawable.stock3,
    Res.drawable.stock4,
    Res.drawable.stock5,
)

val peopleToAvatars: Map<Person, DrawableResource> = demoPeople.zip(demoAvatars).toMap()

@Composable
fun DemoInitializer() {
    val expenseDao: ExpenseDao = koinInject()
    LaunchedEffect(Unit) {
        expenseDao.deleteAll()
        demoPeople.forEach { expenseDao.insert(it) }
        demoExpenses.forEach { expenseDao.insert(it) }
    }
}
