import androidx.room.*
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow 

@Database(entities = [Expense::class], version = 1)
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

// Workaround for an exception...
interface DB {
    fun clearAllTables() {}
}

@Dao
interface ExpenseDao {
    @Query("SELECT * FROM expense")
    fun getAll(): Flow<List<Expense>>
    
    @Insert
    suspend fun insert(expense: Expense)
    
    @Query("DELETE FROM expense")
    suspend fun deleteAll()
}

@Entity(tableName = "expense")
data class Expense(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
)
