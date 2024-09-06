package data

import androidx.room.*

@Entity(
    tableName = "expenses",
    foreignKeys = [
        ForeignKey(
            entity = Person::class,
            parentColumns = ["id"],
            childColumns = ["paidByPersonId"],
        ),
    ],
)
data class Expense(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val paidByPersonId: Long,
    val amount: Int,
    val description: String,
)

@Entity(tableName = "people")
data class Person(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
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
