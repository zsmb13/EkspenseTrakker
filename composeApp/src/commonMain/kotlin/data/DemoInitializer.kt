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
            Expense(randomUUID(), demoPeople[1 - 1].id, 11, "Business trip #1"),
            Expense(randomUUID(), demoPeople[2 - 1].id, 22, "Business trip #2"),
            Expense(randomUUID(), demoPeople[3 - 1].id, 33, "Business trip #3"),
            Expense(randomUUID(), demoPeople[4 - 1].id, 44, "Business trip #4"),
            Expense(randomUUID(), demoPeople[5 - 1].id, 55, "Business trip #5"),
        ).forEach { expenseDao.insert(it) }
    }
}
