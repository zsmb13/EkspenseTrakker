import androidx.room.*

@Entity(
    tableName = "expenses",
    foreignKeys = [
        ForeignKey(
            entity = Person::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("paidByPersonId"),
        ),
    ],
)
data class Expense(
    // TODO replace with Long once https://issuetracker.google.com/issues/341002184 is fixed
    @PrimaryKey
    val id: String,
    // TODO replace with Long once https://issuetracker.google.com/issues/341002184 is fixed
    val paidByPersonId: String,
    val amount: Int,
)

@Entity(tableName = "people")
data class Person(
    // TODO replace with Long once https://issuetracker.google.com/issues/341002184 is fixed
    @PrimaryKey
    val id: String,
    val name: String,
)

data class ExpenseWithPerson(
    @Embedded
    val expense: Expense,

    @Relation(
        parentColumn = "paidByPersonId",
        entityColumn = "id",
    )
    val paidBy: Person,
)
