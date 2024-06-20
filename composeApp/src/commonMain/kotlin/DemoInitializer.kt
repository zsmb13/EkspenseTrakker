import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
fun DemoInitializer(expenseDao: ExpenseDao) {
    LaunchedEffect(Unit) {
        expenseDao.deleteAll()

        val people = listOf(
            Person("random-id-for-alice", "Alice"),
            Person("random-id-for-bob", "Bob"),
            Person("random-id-for-charlie", "Charlie"),
            Person("random-id-for-david", "David"),
            Person("random-id-for-eve", "Eve"),
        )
        people.forEach { expenseDao.insert(it) }

        listOf(
            Expense(randomUUID(), people[1 - 1].id, 11),
            Expense(randomUUID(), people[2 - 1].id, 22),
            Expense(randomUUID(), people[3 - 1].id, 33),
            Expense(randomUUID(), people[4 - 1].id, 44),
            Expense(randomUUID(), people[5 - 1].id, 55),
        ).forEach { expenseDao.insert(it) }
    }
}
