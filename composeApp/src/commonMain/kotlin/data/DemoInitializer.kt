package data

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import ekspensetrakker.composeapp.generated.resources.Res
import ekspensetrakker.composeapp.generated.resources.*
import org.jetbrains.compose.resources.DrawableResource
import org.koin.compose.koinInject
import randomUUID

val demoPeople = listOf(
    Person("random-id-for-a", "Alex"),
    Person("random-id-for-b", "Blake"),
    Person("random-id-for-c", "Casey"),
    Person("random-id-for-d", "Drew"),
    Person("random-id-for-e", "Erin"),
)

val demoExpenses = listOf(
    Expense(randomUUID(), demoPeople.random().id, 129, "Business trip"),
    Expense(randomUUID(), demoPeople.random().id, 12, "Kodee Meet & Greet"),
    Expense(randomUUID(), demoPeople.random().id, 57, "Standard Library Card"),
    Expense(randomUUID(), demoPeople.random().id, 86, "droidcon Moon Tickets"),
    Expense(randomUUID(), demoPeople.random().id, 3, "Cat Tax 🐱"),
    Expense(randomUUID(), demoPeople.random().id, 8, "Omea License"),
    Expense(randomUUID(), demoPeople.random().id, 60, "Multiplatform Multivitamins"),
    Expense(randomUUID(), demoPeople.random().id, 12, "Kotlin in Action, 2nd Edition"),
)

val demoExpensesWithPeople = demoExpenses
    .associateWith { expense -> demoPeople.find { it.id == expense.paidByPersonId }!! }
    .map { (expense, person) -> ExpenseWithPerson(expense, person) }

private val demoAvatars = listOf(
    Res.drawable.stock1,
    Res.drawable.stock2,
    Res.drawable.stock3,
    Res.drawable.stock4,
    Res.drawable.stock5,
)

val peopleToAvatars: Map<Person, DrawableResource> = demoPeople.zip(demoAvatars).toMap()
