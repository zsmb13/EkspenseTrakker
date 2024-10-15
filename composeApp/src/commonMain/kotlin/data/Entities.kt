package data

data class Expense(
    val id: Long = 0L,
    val paidByPersonId: Long,
    val amount: Int,
    val description: String,
)

data class Person(
    val id: Long = 0L,
    val name: String,
)

data class ExpenseWithPerson(
    val expense: Expense,
    val paidBy: Person,
)
