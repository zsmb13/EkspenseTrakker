
data class Expense(
    val id: String,
    val paidByPersonId: String,
    val amount: Int,
    val description: String,
)

data class Person(
    val id: String,
    val name: String,
)
