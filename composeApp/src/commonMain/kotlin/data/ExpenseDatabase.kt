package data

import androidx.room.*
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

expect object MyDatabaseCtor : RoomDatabaseConstructor<ExpenseDatabase>

@Database(entities = [Expense::class, Person::class], version = 1)
@ConstructedBy(MyDatabaseCtor::class)
abstract class ExpenseDatabase : RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao
}

fun RoomDatabase.Builder<ExpenseDatabase>.getRoomDatabase(): ExpenseDatabase {
    return this
        .fallbackToDestructiveMigration(dropAllTables = true)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
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
