import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import ekspensetrakker.composeapp.generated.resources.Res
import ekspensetrakker.composeapp.generated.resources.*
import org.jetbrains.compose.resources.DrawableResource
import org.koin.compose.koinInject

private val demoPeople = listOf(
    Person("random-id-for-a", "Alex"),
    Person("random-id-for-b", "Blake"),
    Person("random-id-for-c", "Casey"),
    Person("random-id-for-d", "Drew "),
    Person("random-id-for-e", "Erin"),
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
fun DemoInitializer(
    expenseDao: ExpenseDao = koinInject(),
) {
    LaunchedEffect(Unit) {
        expenseDao.deleteAll()

        demoPeople.forEach { expenseDao.insert(it) }

        listOf(
            Expense(randomUUID(), demoPeople[1 - 1].id, 129, "Business trip"),
            Expense(randomUUID(), demoPeople[2 - 1].id, 12, "Kodee Meet & Greet"),
            Expense(randomUUID(), demoPeople[3 - 1].id, 57, "Standard Library Card"),
            Expense(randomUUID(), demoPeople[4 - 1].id, 86, "droidCon Moon Tickets"),
            Expense(randomUUID(), demoPeople[5 - 1].id, 3, "Cat Tax 🐱"),
            Expense(randomUUID(), demoPeople[1 - 1].id, 8, "Omea License"),
            Expense(randomUUID(), demoPeople[5 - 1].id, 60, "Multiplatform Multivitamins"),
            Expense(randomUUID(), demoPeople[3 - 1].id, 12, "Kotlin in Action, 2nd Edition"),
        ).forEach { expenseDao.insert(it) }
    }
}
