import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import ekspensetrakker.composeapp.generated.resources.Res
import ekspensetrakker.composeapp.generated.resources.hadi
import org.jetbrains.compose.resources.DrawableResource
import org.koin.compose.koinInject

private val demoPeople = listOf(
    Person("random-id-for-alice", "Alice"),
    Person("random-id-for-bob", "Bob"),
    Person("random-id-for-charlie", "Charlie"),
    Person("random-id-for-david", "David"),
    Person("random-id-for-eve", "Eve"),
)

private val demoAvatars = listOf(
    Res.drawable.hadi,
    Res.drawable.hadi,
    Res.drawable.hadi,
    Res.drawable.hadi,
    Res.drawable.hadi,
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
            Expense(randomUUID(), demoPeople[1 - 1].id, 11),
            Expense(randomUUID(), demoPeople[2 - 1].id, 22),
            Expense(randomUUID(), demoPeople[3 - 1].id, 33),
            Expense(randomUUID(), demoPeople[4 - 1].id, 44),
            Expense(randomUUID(), demoPeople[5 - 1].id, 55),
        ).forEach { expenseDao.insert(it) }
    }
}
