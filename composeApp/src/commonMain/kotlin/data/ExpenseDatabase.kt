package data

import androidx.room.*
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

@Database(entities = [Expense::class, Person::class], version = 1)
abstract class ExpenseDatabase : RoomDatabase(), DB {
    abstract fun expenseDao(): ExpenseDao

    override fun clearAllTables() {
        super.clearAllTables()
    }
}

fun RoomDatabase.Builder<ExpenseDatabase>.getRoomDatabase(): ExpenseDatabase {
    return this
        .fallbackToDestructiveMigration(dropAllTables = true)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}

// TODO remove this workaround when https://issuetracker.google.com/issues/348166275 is fixed
interface DB {
    fun clearAllTables() {}
}

@Dao
interface ExpenseDao {
    @Transaction
    @Query("SELECT * FROM expenses")
    fun getAllWithPeople(): Flow<List<ExpenseWithPerson>>

    @Query("SELECT * FROM people")
    fun getAllPeople(): Flow<List<Person>>

    @Insert
    suspend fun insert(expense: Expense)

    @Insert
    suspend fun insert(person: Person)

    @Transaction
    suspend fun deleteAll() {
        deleteAllExpenses()
        deleteAllPeople()
    }

    @Query("DELETE FROM expenses")
    suspend fun deleteAllExpenses()

    @Query("DELETE FROM people")
    suspend fun deleteAllPeople()
}
